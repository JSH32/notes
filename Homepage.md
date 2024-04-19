---
cssclasses:
  - myhome
banner_x: 0.542
banner_y: 0.708
banner: "[[sky.gif]]"
banner_icon: 📅
---
## Files
```dataview
table file.ctime as Created, file.mtime as "Last modified"
where file.name != this.file.name and file.folder != "Obsidian" and contains(file.path, this.file.folder)
sort file.mtime descending
limit 15
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



