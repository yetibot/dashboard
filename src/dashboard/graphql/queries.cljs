(ns dashboard.graphql.queries)

(def stats
  "query stats($timezone_offset_hours: Int!) {
    stats(timezone_offset_hours: $timezone_offset_hours) {
      uptime
      adapter_count
      user_count
      command_count_today
      command_count
      history_count
      history_count_today
      alias_count
      observer_count
      cron_count
    }
  }")
