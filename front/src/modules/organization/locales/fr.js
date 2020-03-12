export default {
  organization: {
    information: {
      title: "Information",
      label: {
        name: "Nom"
      }
    },
    members: {
      title: "Membres",
      table: {
        firstName: "Prénom",
        lastName: "Nom",
        role: "Rôle"
      },
      manage: "Gérer",
      right: "Droit",
      rightModal: {
        title: "Droit de",
        error: "Une erreur est survenue.",
        OWNER: {
          description: "Peut gérer l'organisation: les membres, les sessions."
        },
        INSTRUCTOR: {
          description:
            "Peut indiquer si les membres sont présents lors d'un session."
        },
        MEMBER: {
          description: "Est inscrit dans l'organisation."
        },
        OFFICE: {
          description:
            "Aide à gérer l'organisation. Peut créer des sessions et changer les droits des membres."
        },
        OFFICE_INSTRUCTOR: {
          description:
            "Aide à gérer l'organisation. Peut créer des sessions et changer les droits des membres. Ainsi que le rôle d'instructeur."
        }
      },
      add: {
        title: "Ajouter un membre",
        success: "Membre ajouté",
        label: {
          email: "Saisir l'adresse mail du membre",
          role: "Role du membre",
          firstName: "Prénom",
          lastName: "Nom"
        },
        alreadyExist: "Cet utilisateur est déjà membre de votre organisation.",
        noMember:
          "Aucun compte n'a était trouvé avec ce mail. Remplissez les champs pour créer un compte avec le membre."
      },
      remove: {
        success: "Membre retiré"
      }
    }
  }
};
