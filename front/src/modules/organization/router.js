import Organization from "@organization/views/Organization";
import Members from "@organization/views/Members";
import Settings from "@organization/views/Settings";

export default [
  {
    path: "/organization/:id",
    component: Organization,
    children: [
      {
        path: "members",
        name: "organization-members",
        component: Members
      },
      {
        path: "settings",
        name: "organization-settings",
        component: Settings
      }
    ]
  }
];
