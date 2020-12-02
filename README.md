# Patient Appointment App

Created an app as part of the B.Tech project which facilitates patients to book doctor’s appointments from their phone. I used Firebase for the database and the app follows Google Material design guidelines to ensure a good app experience. 

## Login flow
- Created authentication flow using firebase for login through mobile number & password. Also attached 2-factor auth using OTP.
- After creating an account user is directed to an activity where he/she can fill his details or skip it. (Can be filled while making an appointment)

## App section & booking flow
- App has the following sections - Booking flow, Booking history & Account
- Booking flow involves selecting date & slot of appointment followed by creating a token for confirmation. The following are details on the different components used 
  - Used Horizontal Calendar for date selection. Slots were then divided into morning & evening using Fragment
  - The date selected was broadcasted to the fragment and the booking activity.
  - The only available slots were allowed to select
  - Based on the selection, the user sees a summary of the slot timing and fee they have to pay
  - On confirmation, a token for the booking is generated and the booking gets added to the phone’s calendar as a reminder
  - App’s homepage gets updated to show the upcoming booking where user can cancel/update slot timings



