export default {
  organization: {
    information: {
      title: "Information",
      label: {
        name: "Name"
      }
    },
    members: {
      title: "Members",
      table: {
        firstName: "First Name",
        lastName: "Last Name",
        role: "Role"
      },
      manage: "Manage",
      right: "Right",
      rightModal: {
        title: "Right of",
        error: "An error has append.",
        OWNER: {
          description: "Can mange the organization: users and sessions."
        },
        INSTRUCTOR: {
          description: "Can say if a user is present at a session."
        },
        MEMBER: {
          description: "Is register in the organization."
        }
      }
    }
  }
};
