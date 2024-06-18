---
cssclasses:
  - myhome
banner_x: 0.542
banner_y: 0.708
banner: "[[sky.gif]]"
banner_icon: 📅
created: 2024-04-19T00:24
updated: 2024-05-26T17:38
---
## Files
```dataview
table created as Created, updated as "Last modified"
where file.name != this.file.name and file.folder != "Obsidian" and contains(file.path, this.file.folder)
sort updated descending
limit 15
```

**OBSIDIAN ACTIVITY**
```dataviewjs
let ftMd = dv.pages("").file.sort(t => t.frontmatter.created)[0]
let total = parseInt([new Date() - new Date(ftMd.frontmatter.created)] / (60*60*24*1000))
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
