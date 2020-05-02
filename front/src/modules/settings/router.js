export default [
  {
    path: "/settings",
    component: () => import("@settings/views/Settings/Settings"),
    children: [
      {
        path: "profile",
        name: "settings-profile",
        component: () => import("@settings/views/Settings/Profile")
      },
      {
        path: "security",
        name: "settings-security",
        component: () => import("@settings/views/Settings/Security")
      },
      {
        path: "organization",
        name: "settings-organization",
        component: () => import("@settings/views/Settings/Organization")
      },
      {
        path: "email",
        name: "settings-email",
        component: () => import("@settings/views/Settings/Email")
      }
    ]
  },
  {
    path: "/settings/organization/create",
    name: "settings-organization-create",
    component: () => import("@settings/views/Settings/OrganizationCreate")
  },
  {
    path: "/change-email",
    name: "change-email",
    component: () => import("@settings/views/ChangeEmail")
  }
];
