export default {
  organization: {
    organization: {
      edit: {
        title: "Modifier l'Organisation",
        success: "Organisation modifiée"
      },
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
          role: "Role du membre"
        },
        alreadyExist: "Cet utilisateur est déjà membre de votre organisation.",
        noMember:
          "Aucun compte n'a était trouvé avec ce mail. Remplissez les champs pour créer un compte avec le membre."
      },
      remove: {
        success: "Membre retiré"
      }
    },
    settings: {
      title: "Paramètres",
      stripe: {
        title: "Changer de moyen de paiement",
        description:
          "Vous pouvez changer le moyen de paiement de votre organisation",
        button: "Changer",
        success: "Moyen de paiement modifié",
        invalid:
          "La carte actuel n'est plus valide. Merci de changer pour continuer sur Miimber."
      },
      archive: {
        title: "Archiver l'organisation",
        description:
          "Une organisation archivée n'est pas récupérable. Plus aucun élément de l'organisation ne sera accessible. Le paiement s'arrête avant la prochaine échéance.",
        button: "Archiver votre organisation",
        success: "Organisation archivée"
      }
    },
    sessions: {
      title: "Sessions",
      table: {
        title: "Titre",
        date: "Jour",
        start: "Début",
        end: "Fin",
        typeSession: "Catégorie"
      },
      add: {
        title: "Ajouter une session",
        success: "Session ajouté"
      },
      edit: {
        title: "Modifier une session",
        success: "Session modifié"
      },
      delete: {
        title: "Supprimer une session",
        message: "Êtez-vous sur de supprimer la session ?",
        success: "Session supprimé"
      },
      label: {
        startHour: "Heure de début",
        endHour: "Heure de fin",
        sessionDate: "Date de la session",
        startDate: "Date de début",
        endDate: "Date de fin",
        typeSession: "Catégorie",
        recurrence: "Récurrence",
        title: "Titre",
        repeat: "Répéter toutes les",
        description: "Description",
        search: "Rechercher",
        between: "Entre",
        hasLimit: "Nombre max. de participants ?",
        limit: "Limite"
      }
    },
    typeSessions: {
      title: "Catégories",
      table: {
        name: "Nom"
      },
      add: {
        title: "Ajouter une catégorie",
        success: "Catégorie ajoutée"
      },
      edit: {
        title: "Modifier une catégorie",
        success: "Catégorie modifiée"
      },
      delete: {
        title: "Supprimer une catégorie",
        message: "Êtez-vous sur de supprimer la catégorie ?",
        success: "Catégorie supprimée"
      },
      label: {
        name: "Nom"
      }
    },
    forum: {
      title: "Forum",
      label: {
        title: "Titre",
        add: "Ajouter une categorie"
      },
      add: {
        title: "Ajouté une categorie",
        success: "Categorie ajouté"
      },
      delete: {
        title: "Supprimer une catégorie",
        message: "Êtez-vous sur de supprimer la catégorie ?",
        success: "Catégorie supprimée"
      }
    },
    subjectforum: {
      label: {
        title: "Titre",
        add: "Ajouter un sujet"
      },
      add: {
        title: "Ajouté un sujet",
        success: "Sujet ajouté"
      },
      delete: {
        title: "Supprimer un sujet",
        message: "Êtez-vous sur de supprimer le sujet ?",
        success: "Sujet supprimée"
      },
      edit: {
        title: "Modifier un sujet",
        success: "Sujet modifiée"
      }
    }
  }
};
