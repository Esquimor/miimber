import Organization from "@organization/views/Organization";
import Members from "@organization/views/Members";
import Settings from "@organization/views/Settings";
import Sessions from "@organization/views/Sessions/Sessions";
import TypeSessions from "@organization/views/Sessions/TypeSessions";

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
      },
      {
        path: "sessions",
        name: "organization-sessions",
        component: Sessions
      },
      {
        path: "sessions/types",
        name: "organization-sessions-types",
        component: TypeSessions
      }
    ]
  }
];
