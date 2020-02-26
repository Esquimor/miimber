import Settings from "@settings/views/Settings";
import Profile from "@settings/views/Profile";

export default [
  {
    path: "/settings",
    component: Settings,
    children: [
      {
        path: "profile",
        name: "settings-profile",
        component: Profile
      }
    ]
  }
];
