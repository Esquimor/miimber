import Organization from "@organization/views/Organization";
import Members from "@organization/views/Members";

export default [
  {
    path: "/organization/:id",
    component: Organization,
    children: [
      {
        path: "members",
        name: "organization-members",
        component: Members
      }
    ]
  }
];
