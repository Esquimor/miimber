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
        },
        OFFICE: {
          description:
            "Help to manage the organization. Can create session and change user right."
        },
        OFFICE_INSTRUCTOR: {
          description:
            "Help to manage the organization. Can create session and change user right. Is also a instructor."
        }
      },
      add: {
        title: "Add a member",
        success: "Member added",
        label: {
          email: "Enter Email Address",
          role: "Role of the member",
          firstName: "First Name",
          lastName: "Last Name"
        },
        alreadyExist: "This user is already a member of your organization.",
        noMember:
          "No account was found with this email. Fill the fields to create an account with the member."
      }
    }
  }
};
