import Settings from "@settings/views/Settings/Settings";
import Profile from "@settings/views/Settings/Profile";
//import Account from "@settings/views/Settings/Account";
import Security from "@settings/views/Settings/Security";
import Organization from "@settings/views/Settings/Organization";
import OrganizationCreate from "@settings/views/Settings/OrganizationCreate";
import Email from "@settings/views/Settings/Email";

export default [
  {
    path: "/settings",
    component: Settings,
    children: [
      {
        path: "profile",
        name: "settings-profile",
        component: Profile,
      },
      /*{
        path: "account",
        name: "settings-account",
        component: Account
      },*/
      {
        path: "security",
        name: "settings-security",
        component: Security,
      },
      {
        path: "organization",
        name: "settings-organization",
        component: Organization,
      },
      {
        path: "email",
        name: "settings-email",
        component: Email,
      },
    ],
  },
  {
    path: "/settings/organization/create",
    name: "settings-organization-create",
    component: OrganizationCreate,
  },
];
