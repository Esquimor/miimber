import Organization from "@organization/views/Organization";
import Members from "@organization/views/Members";
import Settings from "@organization/views/Settings";
import Sessions from "@organization/views/Sessions/Sessions";
import TypeSessions from "@organization/views/Sessions/TypeSessions";

export default [
  {
    path: "/organization-manage/:id",
    component: Organization,
    children: [
      {
        path: "members",
        name: "organization-manage-members",
        component: Members
      },
      {
        path: "settings",
        name: "organization-manage-settings",
        component: Settings
      },
      {
        path: "sessions",
        name: "organization-manage-sessions",
        component: Sessions
      },
      {
        path: "sessions/categories",
        name: "organization-manage-sessions-types",
        component: TypeSessions
      }
    ]
  }
];
