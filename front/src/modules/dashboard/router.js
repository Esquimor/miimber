import Dashboard from "@dashboard/views/Dashboard";
import Home from "@dashboard/views/Home";

import Sessions from "@dashboard/views/session/Sessions";
import Session from "@dashboard/views/session/Session";
import SessionEmerge from "@dashboard/views/session/SessionEmerge";
import SessionInformation from "@dashboard/views/session/SessionInformation";
//import SessionAttendee from "@dashboard/views/session/SessionAttendee";
import SessionRegistered from "@dashboard/views/session/SessionRegistered";

import Organizations from "@dashboard/views/organization/Organizations";
import Organization from "@dashboard/views/organization/Organization";
import OrganizationSessions from "@dashboard/views/organization/OrganizationSessions";
import OrganizationMembers from "@dashboard/views/organization/OrganizationMembers";

export default [
  {
    path: "/dashboard",
    component: Dashboard,
    children: [
      {
        path: "",
        name: "dashboard-home",
        component: Home
      },
      {
        path: "session",
        name: "dashboard-sessions",
        component: Sessions
      },
      {
        path: "session/:id",
        component: Session,
        children: [
          {
            path: "emerge",
            name: "dashboard-session-emerge",
            component: SessionEmerge
          },
          {
            path: "information",
            name: "dashboard-session-information",
            component: SessionInformation
          },
          /**{
            path: "attendee",
            name: "dashboard-session-attendee",
            component: SessionAttendee
          },*/
          {
            path: "registered",
            name: "dashboard-session-registered",
            component: SessionRegistered
          }
        ]
      },
      {
        path: "organization",
        name: "dashboard-organizations",
        component: Organizations
      },
      {
        path: "organization/:id",
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
    ]
  }
];
