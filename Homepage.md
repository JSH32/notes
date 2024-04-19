---
cssclasses:
  - myhome
banner_x: 0.62858
banner_y: 0.38648
target: 10000
banner: "[[flowers.gif]]"
banner_icon: 📅
---

- **LIFE**
	- [[00. Library|Library]]
	- [[Qin|Myself]]
	- [[00. Files|Files]]
	- [[00. myCodes|Codes]]
- **WORK**
	- [[00. Study|Study]]
	- [[00. Work|Work]]
	- [[00. Cardbox|Cardbox]]
	- [[00. References|References]]
- **PEOPLE**
	- [[00. People|People]]
	- [[00. Pets|Pets]]

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



