export default {
  data() {
    return {
      monthNames: [
        this.$t("core.months.JANUARY"),
        this.$t("core.months.FEBRUARY"),
        this.$t("core.months.MARCH"),
        this.$t("core.months.APRIL"),
        this.$t("core.months.MAY"),
        this.$t("core.months.JUNE"),
        this.$t("core.months.JULY"),
        this.$t("core.months.AUGUST"),
        this.$t("core.months.SEPTEMBER"),
        this.$t("core.months.OCTOBER"),
        this.$t("core.months.NOVEMBER"),
        this.$t("core.months.DECEMBER")
      ],
      dayNames: [
        this.$t("core.daysShort.SUNDAY"),
        this.$t("core.daysShort.MONDAY"),
        this.$t("core.daysShort.TUESDAY"),
        this.$t("core.daysShort.WEDNESDAY"),
        this.$t("core.daysShort.THURSDAY"),
        this.$t("core.daysShort.FRIDAY"),
        this.$t("core.daysShort.SATURDAY")
      ]
    };
  }
};
