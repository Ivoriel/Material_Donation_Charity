\Basic internet portal project.

Goal of the portal is to allow passing information about donations to the relevant institutions, so they can pick them up.

The first functionality is for the user to be able to create a donation specifying the institution
to which they wish to donate (out of a list of serviced institutions), the donation info (what is donated
and in what amounts) as well as pick-up address and time info.

For that to be possible a nicely readable list of institutions was created on the main page to which donations can be made.

For the donations to be placed, the donor has to register an account, therefore registration and login have been enabled.

Because of the need to control certain aspects of the portal that shouldn't be available to all users roles are being
implemented that will gate areas of the app.

Initially implemented roles will be _Administrator_ and _User_ with _Institutional User_ following shortly after. 
_Administrator_ will have the access to the whole portal and all possible option (superuser).
_User_ will be allowed to control their donations.
The _Institutional user_ will be able to control their donations and their confirmed institution information. 
New functionalities will be built with the roles kept in mind.

To properly gate access login and admin filters will be implemented to redirect users that are unregistered, not logged in,
or don't have the permissions required to enter certain areas of the portal. For proper supervision pages with lists of 
users and institutions will be created with extended control options. 

Dedicated donation editing and control pages will be created for the users as well as the institution users. Control over
customization f elements of the donation form might be granted to the institution users. 