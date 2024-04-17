### Limits and Asymptotes
#### Definitions:
1. **Limits**: The concept of a limit is used to quantify how the function $f(x)$ behaves as $x$ approaches a certain value.
 
2. **Infinite Limit**: If the value of function $f(x)$ grows without bound as $x$ approaches a certain value, this scenario is termed as having an "infinite limit". The notation is usually $\underset{x \rightarrow a}{lim} f(x) = \infty$ or $\underset{x \rightarrow a}{lim} f(x) = -\infty$. But technically, we say the Limit Does Not Exist ($DNE$) due to the unbounded nature of the function. 
   - Example: $\underset{x \rightarrow 0}{lim} (\frac{1}{x^2}) = \infty$ signifies that the value of $\frac{1}{x^2}$ grows without bound as $x$ gets closer to 0.

3. **Vertical Asymptote**:  A line defined by $x=a$ will be called a vertical asymptote of $y=f(x)$ if any of the following hold true.
   - $\underset{x \rightarrow a}{lim} f(x)=\infty$
   - $\underset{x \rightarrow a}{lim} f(x)=-\infty$

#### Concept clarity with examples:
1. Consider the function $f(x)=\frac{x^2-1}{x-1}$.
   - This function can be factored into $f(x) = \frac{(x-1)(x+1)}{x-1}$.
   - For all $x \ne 1$, the function simplifies to $f(x)=x+1$.
   - Therefore, as $x$ approaches $1$, $\underset{x \rightarrow 1}{lim}\:f(x) = 2$.

2. Let's analyze the function $f(x) = \frac{1}{x-3}$ as $x$ gets closer to $3$.
   - Here, the limit does not exist since the function never actually reaches the value $3$. It tends towards $\infty$ from one side ($x>3$) and $-\infty$ from another side ($x<3$).
   - Hence, the line $x=3$ is called a vertical asymptote as it satisfies the conditions for defining vertical asymptotes. The graph of the function visually confirms this point. 

```desmos-graph
y=\frac{1}{x-3}

```

### Tangent function, $y=tan(x)$

The tangent function can be defined as the ratio of the sine of x to the cosine of x, i.e., 

$$y=tan(x)=\frac{sin(x)}{cos(x)}$$

```desmos-graph
y=\frac{\sin(x)}{\cos(x)}
```

**Key characteristics of the Tangent function:**

- Vertical Asymptotes (infinitely many) occur at odd multiples of ${\pi}/{2}$, i.e., where the function is undefined due to division by zero.

### Sinc function, $f(x)=\frac{sin(x)}{x}$

The sinc function has an interesting property - it is $undefined$ at $x=0$, but the limit as x approaches zero is actually 1.

```desmos-graph
y=\frac{\sin(x)}{x}
\left(0,1\right)|open|label:(0, undefined)
```


**Key characteristics of the Sinc function:**

- At $x=0$, there is no vertical asymptote, but instead a hole. 
- $\underset{x \rightarrow 0}{lim} \frac{sin(x)}{x}=1$. This shows that as x gets closer and closer to 0, the value of the function approaches 1.

### Limit Laws

We can use these laws to break down complex limits into more manageable parts. The laws are as follows:

- **Addition/Subtraction**
$\underset{x \rightarrow a}{lim}\: [f(x) \pm g(x)] = \underset{x \rightarrow a}{lim}\:f(x) \pm \underset{x \rightarrow a}{lim}\:g(x)$
  
- **Multiplication**
$\underset{x \rightarrow a}{lim}\: [f(x) \cdot g(x)] = [\underset{x \rightarrow a}{lim}\:f(x)] \cdot [\underset{x \rightarrow a}{lim}\:g(x)]$

- **Division**
$\underset{x \rightarrow a}{lim} \frac{f(x)}{g(x)} = \frac{\underset{x \rightarrow a}{lim}\:f(x)}{\underset{x \rightarrow a}{lim}\:g(x)}$, 
  
  *A small caveat here - we must ensure that $\underset{x \rightarrow a}{lim}\:g(x) \ne 0$ (The limit of the denominator should not be zero).*
  
- **Constant Multiple**
$\underset{x \rightarrow a}{lim} [c \cdot f(x)] = c \cdot (\underset{x \rightarrow a}{lim} \: f(x))$

- And finally,
$\underset{x \rightarrow a}{lim} (x)= a$,   i.e., the limit of $x$ as $x$ tends to $a$ is $a$.