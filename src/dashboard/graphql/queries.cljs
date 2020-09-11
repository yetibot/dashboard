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

(def history
  "query history($timezone_offset_hours: Int!, $yetibot_only: Boolean!, $commands_only: Boolean!, $search_query: String) {
    stats(timezone_offset_hours: $timezone_offset_hours) {
      history_count
    }

    history(limit: 30, offset: 0,
      commands_only: $commands_only,
      yetibot_only: $yetibot_only,
      search_query: $search_query
    ) {
      id
      chat_source_adapter
      chat_source_room
      command
      correlation_id
      created_at
      user_name
      is_command
      is_yetibot
      body
      user_id
      user_name
    }
  }")

(def history-item
  "query history_item($history_id: String!) {
    history_item(id: $history_id) {
      id
    }
  }")
