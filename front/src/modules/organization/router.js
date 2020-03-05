import Organization from "@organization/views/Organization";
import General from "@organization/views/General";

export default [
  {
    path: "/organization/:id",
    component: Organization,
    children: [
      {
        path: "general",
        name: "organization-general",
        component: General
      }
    ]
  }
];
