export default {
  organization: {
    organization: {
      edit: {
        title: "Edit Organization",
        success: "Organization edited"
      },
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
          role: "Role of the member"
        },
        alreadyExist: "This user is already a member of your organization.",
        noMember:
          "No account was found with this email. Fill the fields to create an account with the member."
      },
      remove: {
        success: "Member removed"
      }
    },
    settings: {
      title: "Settings",
      stripe: {
        title: "Change payment method",
        description: "You can change the payment method of your organization",
        button: "Change",
        success: "Modified payment method",
        invalid:
          "The current card is no longer valid. Please change to continue on Miimber."
      },
      archive: {
        title: "Archive organization",
        description:
          "An archived organization is not recoverable. No more elements of the organization will be accessible. Payment stops before the next due date.",
        button: "Archive your organization",
        success: "Organization archived"
      }
    },
    sessions: {
      title: "Sessions",
      table: {
        title: "Title",
        date: "Day",
        start: "Start",
        end: "End",
        typeSession: "Category"
      },
      add: {
        title: "Add a session",
        success: "Session added"
      },
      edit: {
        title: "Edit a session",
        success: "Session edited"
      },
      delete: {
        title: "Delete a session",
        message: "Do you really want to delete this session ?",
        success: "Session deleted"
      },
      label: {
        startHour: "Start time",
        endHour: "End time",
        sessionDate: "Date of the session",
        startDate: "Start date",
        endDate: "End date",
        typeSession: "Category",
        recurrence: "Recurrence",
        title: "Title",
        repeat: "Repeat every",
        description: "Description",
        search: "Search",
        between: "Between",
        hasLimit: "Max. number of participants ?",
        limit: "Limit"
      }
    },
    typeSessions: {
      title: "Categories",
      table: {
        name: "Name"
      },
      add: {
        title: "Add a category",
        success: "Category added"
      },
      edit: {
        title: "Edit a category",
        success: "Category edited"
      },
      delete: {
        title: "Delete a category",
        message: "Do you really want to delete this category ?",
        success: "Category deleted"
      },
      label: {
        name: "Name"
      }
    },
    forum: {
      title: "Forum",
      label: {
        title: "Title",
        add: "Add a category"
      },
      add: {
        title: "Add a category",
        success: "Category added"
      },
      delete: {
        title: "Delete a category",
        message: "Do you really want to delete this category ?",
        success: "Category deleted"
      },
      edit: {
        success: "Category edited"
      }
    },
    subjectforum: {
      label: {
        title: "Title",
        add: "Add a subject"
      },
      add: {
        title: "Add a subject",
        success: "Subject added"
      },
      delete: {
        title: "Delete a subject",
        message: "Do you really want to delete this subject ?",
        success: "Subject deleted"
      },
      edit: {
        title: "Edit a subject",
        success: "Subject edited"
      }
    }
  }
};
