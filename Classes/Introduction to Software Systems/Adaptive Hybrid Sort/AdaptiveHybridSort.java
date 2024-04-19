import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h1>Adaptive Hybrid Sort</h1>
 * Implements an adaptive hybrid sorting algorithm that dynamically selects
 * between QuickSort and TimSort based on past runtime performance, utilizing
 * Java's Fork/Join framework. This class extends {@link RecursiveAction} and is
 * intended for sorting lists of elements that implement the {@link Comparable}
 * interface, optimizing utilization of multi-core processors through concurrent
 * execution.
 *
 * <h2>Usage:</h2>
 * <pre>{@code
 * List<Integer> data = Arrays.asList(8, 3, 5, 9, 1, 10, 4);
 * AdaptiveHybridSort.parallelSort(data, true);
 * }</pre>
 *
 * <h2>Diagram of Process:</h2>
 * <pre>
 * [ Initial List ]
 *        |
 *        |--- [ Evaluate Segment ]
 *        |
 *        |--- [ Segment Size < Threshold ]
 *                |
 *                |--- [ Use TimSort or QuickSort based on Metrics ]
 *                |
 *        |--- [ Segment Size >= Threshold ]
 *                |
 *                |--- [ Split into Two Tasks ]
 *                        |
 *                        |--- [ Recursive Divide & Conquer ]
 *                                   |
 *                                   |--- [ Merge Segments ]
 *                                   |
 *                                   |--- [ Sorted List ]
 * </pre>
 *
 * <p>This method capitalizes on parallel processing capabilities to
 * substantially decrease sorting times on multi-core systems by concurrently
 * handling list segments with the most efficient sorting algorithm determined
 * at runtime.</p>
 *
 * <h2>Dynamic Algorithm Selection:</h2>
 * <p>Instead of solely relying on a single sorting algorithm, this hybrid
 * approach decides between QuickSort and TimSort depending on their current
 * performance metrics which are dynamically updated during runtime:</p> <ul>
 *     <li>Performance metrics (average time and variance of sorting times) for
 * QuickSort and TimSort are constantly updated.</li> <li>The decision to use
 * TimSort or QuickSort for a given segment is made based on these metrics.</li>
 * </ul>
 *
 * <h2>Performance Adjustment:</h2>
 * <p>This implementation includes a method to adjust the threshold size that
 * determines whether a task should be processed in parallel or handled
 * sequentially:</p> <ul> <li>The threshold adjustment is based on captured
 * performance data comparing small and large parallel tasks.</li> <li>Periodic
 * adjustments ensure optimal performance by increasing or decreasing the
 * threshold based on average task durations.</li> <li>The system optimizes for
 * either smaller, faster tasks or fewer, larger tasks as per current execution
 * metrics.</li>
 * </ul>
 *
 * @author JSH32
 * @since  2024-04-31
 */
public class AdaptiveHybridSort<T extends Comparable<T>>
    extends RecursiveAction {
  private static final AtomicLong THRESHOLD = new AtomicLong(5000);

  // Metrics to determine global performance of algorithms.
  private static final SortMetrics quickSortMetrics = new SortMetrics();
  private static final SortMetrics timSortMetrics = new SortMetrics();

  // A large task is quantified as a task with items greater than THRESHOLD
  // These are used to dynamically recompute THRESHOLD
  private static final AtomicLong smallTaskCount = new AtomicLong(0);
  private static final AtomicLong largeTaskCount = new AtomicLong(0);
  private static final AtomicLong smallTaskTime = new AtomicLong(0);
  private static final AtomicLong largeTaskTime = new AtomicLong(0);

  // List which we mutably modify.
  private final List<T> list;
  private final int left;
  private final int right;

  /**
   * Constructs an AdaptiveHybridSort for a segment of a list.
   *
   * @param list  the list to be sorted
   * @param left  the index of the leftmost element to sort
   * @param right the index of the rightmost element to sort
   */
  private AdaptiveHybridSort(List<T> list, int left, int right) {
    this.list = list;
    this.left = left;
    this.right = right;
  }

  /**
   * Sorts the given list using parallel sorting and optionally profiles the
   * sort operations.
   *
   * @param list the list to be sorted
   */
  public static <T extends Comparable<T>> void parallelSort(List<T> list) {
    try (ForkJoinPool pool = new ForkJoinPool()) {
      pool.invoke(new AdaptiveHybridSort<>(list, 0, list.size() - 1));
    }
  }

  /**
   * Metrics class for recording and calculating the average time and variance
   * of sorting runs.
   */
  public static class SortMetrics {
    private final ConcurrentLinkedQueue<Long> timeRecords =
        new ConcurrentLinkedQueue<>();
    private long totalDuration = 0;
    private long totalSquaredDuration = 0;

    /**
     * Updates the metrics with a new duration, maintains only the most recent
     * 100 entries.
     *
     * @param duration the duration of a sorting run
     */
    public void update(long duration) {
      timeRecords.add(duration);
      totalDuration += duration;
      totalSquaredDuration += duration * duration;

      while (timeRecords.size() > 100) {
        Long removed = timeRecords.remove();
        if (removed != null) {
          totalDuration -= removed;
        }
      }
    }

    /**
     * Calculates the average duration of the recorded sorting durations.
     *
     * @return the average duration.
     */
    public double getAverageTime() {
      if (timeRecords.isEmpty())
        return 0;
      return totalDuration / (double)timeRecords.size();
    }

    /**
     * Calculates the variance of the recorded sorting durations.
     *
     * @return the variance of durations.
     */
    public double getVariance() {
      if (timeRecords.size() <= 1)
        return 0;
      double mean = getAverageTime();
      return (totalSquaredDuration / (double)timeRecords.size()) -
          (mean * mean);
    }
  }

  /**
   * Determines whether to use TimSort based on recorded performance metrics.
   *
   * @return true if TimSort should be used, false otherwise.
   */
  private boolean shouldUseTimSort() {
    double quickSortAvg = quickSortMetrics.getAverageTime();
    double timSortAvg = timSortMetrics.getAverageTime();
    double quickSortVar = quickSortMetrics.getVariance();
    double timSortVar = timSortMetrics.getVariance();

    double quickSortScore = quickSortAvg + quickSortVar;
    double timSortScore = timSortAvg + timSortVar;

    return timSortScore <= quickSortScore;
  }

  /**
   * Overrides the RecursiveAction's compute method to perform adaptive sorting
   * by automatically choosing between quickSort or TimSort based on the data
   * size and previous performance.
   */
  @Override
  protected void compute() {
    if ((right - left) < THRESHOLD.get()) {
      long startTime = System.nanoTime();
      if (shouldUseTimSort()) {
        timSort(list, left, right);
        timSortMetrics.update(System.nanoTime() - startTime);
      } else {
        quickSort(list, left, right);
        quickSortMetrics.update(System.nanoTime() - startTime);
      }
      smallTaskCount.incrementAndGet();
      smallTaskTime.addAndGet(System.nanoTime() - startTime);
    } else {
      largeTaskCount.incrementAndGet();
      long startTime = System.nanoTime();
      int mid = left + (right - left) / 2;
      AdaptiveHybridSort<T> leftTask =
          new AdaptiveHybridSort<>(list, left, mid);
      AdaptiveHybridSort<T> rightTask =
          new AdaptiveHybridSort<>(list, mid + 1, right);
      invokeAll(leftTask, rightTask);
      mergeRuns(list, left, mid, right);
      largeTaskTime.addAndGet(System.nanoTime() - startTime);
    }

    adjustThreshold();
  }

  /**
   * Adjusts the threshold size for deciding whether to treat a task as small or
   * large based on average times of completion.
   */
  private void adjustThreshold() {
    long smallAvg = smallTaskCount.get() == 0
                        ? 0
                        : smallTaskTime.get() / smallTaskCount.get();
    long largeAvg = largeTaskCount.get() == 0
                        ? 0
                        : largeTaskTime.get() / largeTaskCount.get();

    if (smallAvg * 1.2 < largeAvg) {
      THRESHOLD.updateAndGet(val -> Math.max(1000, val - 500));
    } else if (largeAvg < smallAvg) {
      THRESHOLD.updateAndGet(val -> val + 500);
    }
  }

  /**
   * Merges two sorted runs.
   *
   * @param list  the list containing the runs to merge
   * @param left  the starting index of the first sorted run
   * @param mid   the ending index of the first sorted run
   * @param right the ending index of the second sorted run
   */
  private void mergeRuns(List<T> list, int left, int mid, int right) {
    List<T> temp = new ArrayList<>(list.subList(left, right + 1));
    int i = 0, j = mid - left + 1, k = left;
    while (i <= mid - left && j <= right - left) {
      if (temp.get(i).compareTo(temp.get(j)) <= 0) {
        list.set(k, temp.get(i++));
      } else {
        list.set(k, temp.get(j++));
      }
      k++;
    }

    while (i <= mid - left) {
      list.set(k++, temp.get(i++));
    }
    while (j <= right - left) {
      list.set(k++, temp.get(j++));
    }
  }

  /**
   * Implements a simple QuickSort algorithm on a sublist.
   *
   * @param list  the list to be sorted
   * @param start the starting index for the quicksort
   * @param end   the ending index for the quicksort
   */
  private void quickSort(List<T> list, int start, int end) {
    if (start < end) {
      int p = partition(list, start, end);
      quickSort(list, start, p - 1);
      quickSort(list, p + 1, end);
    }
  }

  /**
   * Partitions the list around a pivot for QuickSort, returning the index of
   * the pivot.
   *
   * @param list  the list to be partitioned
   * @param start the starting index for the partitioning
   * @param end   the ending index for the partitioning
   * @return the final index of the pivot
   */
  private int partition(List<T> list, int start, int end) {
    T pivot = list.get(end);
    int i = start - 1;
    for (int j = start; j < end; j++) {
      if (list.get(j).compareTo(pivot) <= 0) {
        i++;
        swap(list, i, j);
      }
    }
    swap(list, i + 1, end);
    return i + 1;
  }

  /**
   * Swaps two elements in a list.
   *
   * @param list the list where the swap will occur
   * @param i    the index of the first element to swap
   * @param j    the index of the second element to swap
   */
  private void swap(List<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  /**
   * Implements TimSort on a section of a list using a combination of insertion
   * sort for small arrays and merging of sorted runs.
   *
   * @param list  the list to be sorted using TimSort
   * @param left  the index of the leftmost element to start sorting
   * @param right the index of the rightmost element to end sorting
   */
  private void timSort(List<T> list, int left, int right) {
    int run = 32;
    for (int i = left; i <= right; i += run) {
      insertionSort(list, i, Math.min((i + 31), right));
    }

    for (int size = run; size < right - left + 1; size = 2 * size) {
      for (int l = left; l <= right; l += (2 * size)) {
        int m = l + size - 1;
        if (m >= right)
          break;
        int r = Math.min(l + 2 * size - 1, right);

        mergeRuns(list, l, m, r);
      }
    }
  }

  /**
   * Implements insertion sort on a subsection of a list.
   *
   * @param list  the list to apply insertion sort
   * @param left  the starting index for insertion sort
   * @param right the ending index for insertion sort
   */
  private void insertionSort(List<T> list, int left, int right) {
    for (int i = left + 1; i <= right; i++) {
      T temp = list.get(i);
      int j = i - 1;
      while (j >= left && list.get(j).compareTo(temp) > 0) {
        list.set(j + 1, list.get(j));
        j--;
      }
      list.set(j + 1, temp);
    }
  }
}