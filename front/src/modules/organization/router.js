import Organization from "@organization/views/Organization";
import Information from "@organization/views/Information";
import Members from "@organization/views/Members";

export default [
  {
    path: "/organization/:id",
    component: Organization,
    children: [
      {
        path: "information",
        name: "organization-information",
        component: Information
      },
      {
        path: "members",
        name: "organization-members",
        component: Members
      }
    ]
  }
];
