---
created: 2024-04-17T16:50
updated: 2024-04-19T11:26
banner: "[[iuluddy.jpg]]"
banner_icon: 📋
banner_x: 0.662
---
## IU Attendance Tracking System
### Technologies Used
- **Database** - MySQL
- **Communication** - tRPC
- **Server** - Fastify
- **Client**
	- NextJS
		- https://trpc.io/docs/client/nextjs - Works with tRPC natively
### Form Types
- Admin
	- Create Event - All forms are owned by events
	- Share Event - Link or QR code
- RSVP Form
	- Have basic ability to gather attendees, login with IU which gets email with dietary restrictions for food ordering.
- Check-In
	- Allow people to check in, forms can have different questions as well
		- If RSVP is set to required then 
- Engagement
	- If engagement is enabled, cookie/localstorage is stored and connects to websocket
		- Screen says "Keep this page open"
		- Event controller can do a tophat/kahoot like thing, where for 30 seconds a question appears on a screen, all connected devices will connect and have to answer from options shown.
	- If engagement is not enabled, page will say "Thank You!"