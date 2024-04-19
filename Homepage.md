---
cssclasses:
  - myhome
banner_x: 0.62858
banner_y: 0.38648
target: 10000
banner: "[[flowers.gif]]"
banner_icon: 📅
---
# gh
asd




```jsx:
<ShowI></ShowI>
```



**OBSIDIAN ACTIVITY**
```dataviewjs

let ftMd = dv.pages("").file.sort(t => t.cday)[0]
let total = parseInt([new Date() - ftMd.ctime] / (60*60*24*1000))
let totalDays = "You have been using *Obsidian* for "+total+" days,"
let nofold = '!"misc/templates"'
let allFile = dv.pages(nofold).file
let totalMd = " with "+
	allFile.length+" notes, "
let totalTag = allFile.etags.distinct().length+" tags, "
let totalTask = allFile.tasks.length+" tasks created. "
dv.paragraph(
	totalDays+totalMd+""+totalTag+""+totalTask
)

```



