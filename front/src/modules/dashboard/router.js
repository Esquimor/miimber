import Home from "@dashboard/views/Home";

import Sessions from "@dashboard/views/session/Sessions";
import Session from "@dashboard/views/session/Session";
import SessionEmerge from "@dashboard/views/session/SessionEmerge";

import Organizations from "@dashboard/views/organization/Organizations";
import Organization from "@dashboard/views/organization/Organization";
import OrganizationSessions from "@dashboard/views/organization/OrganizationSessions";
import OrganizationMembers from "@dashboard/views/organization/OrganizationMembers";

export default [
  {
    path: "/dashboard",
    name: "dashboard-home",
    component: Home
  },
  {
    path: "/session",
    name: "dashboard-sessions",
    component: Sessions
  },
  {
    path: "/session/:id",
    name: "dashboard-session",
    component: Session
  },
  {
    path: "/session/:id/emerge",
    name: "dashboard-session-emerge",
    component: SessionEmerge
  },
  {
    path: "/organization",
    name: "dashboard-organizations",
    component: Organizations
  },
  {
    path: "/organization/:id",
    component: Organization,
    children: [
      {
        path: "sessions",
        name: "dashboard-organization-sessions",
        component: OrganizationSessions
      },
      {
        path: "members",
        name: "dashboard-organization-members",
        component: OrganizationMembers
      }
    ]
  }
];
