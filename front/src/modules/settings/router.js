import Settings from "@settings/views/Settings";
import Profile from "@settings/views/Profile";
//import Account from "@settings/views/Account";
import Security from "@settings/views/Security";
import Organization from "@settings/views/Organization";
import OrganizationCreate from "@settings/views/OrganizationCreate";

export default [
  {
    path: "/settings",
    component: Settings,
    children: [
      {
        path: "profile",
        name: "settings-profile",
        component: Profile
      },
      /*{
        path: "account",
        name: "settings-account",
        component: Account
      },*/
      {
        path: "security",
        name: "settings-security",
        component: Security
      },
      {
        path: "organization",
        name: "settings-organization",
        component: Organization
      }
    ]
  },
  {
    path: "/settings/organization/create",
    name: "settings-organization-create",
    component: OrganizationCreate
  }
];
