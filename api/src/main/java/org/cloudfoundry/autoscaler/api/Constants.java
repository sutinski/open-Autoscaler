package org.cloudfoundry.autoscaler.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashSet;






import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.cloudfoundry.autoscaler.api.rest.PublicRestApi;
import org.cloudfoundry.autoscaler.api.util.ConfigManager;

public final class Constants {

	private static final ObjectMapper mapper = new ObjectMapper();

    public static final String APP_ID = "appID";
    public static final String APP_NAME = "appName";
    public static final String APPS = "apps";
    public static final String WARDEN_IP = "wardenIP";
    public static final String INSTANCE_INDEX = "instanceIndex";
    public static final String FLAG = "flag";

    public static final String DEA = "dea";
    public static final String ZONE = "zone";
    public static final String INSTANCES = "instances";

    public static final String POLICY_COOLDOWN = "policy.cooldown";
    public static final String POLICY_DURATION = "policy.duration";
    public static final String MIN_INSTANCES = "policy.min_instances";
    public static final String MAX_INSTANCES = "policy.max_instances";
    public static final String THRESHOLD_LOW = "policy.threshold.low.";
    public static final String THRESHOLD_HIGH = "policy.threshold.high.";

    public static final String ZONES = "zones";
    public static final String TYPE = "type";
    public static final String MAX = "max";
    public static final String MIN = "min";
    public static final String POLICIES = "policies";
    public static final String COOL_DOWN = "cool_down";
    public static final String DURATION = "duration";

    public static final int DASHBORAD_TIME_RANGE = 30;

    //metric db rollout frequency
    public static final String HOURLY = "hourly";
    public static final String DAILY = "daily";
    public static final String WEEKLY = "weekly";
    public static final String MONTHLY = "monthly";
    public static final String CUSTOM = "custom";
    public static final String CONTINUOUS = "continuously";

    // config keys
    public static final String POLICY_CONNECT_TIMEOUT = "policy.connect.timeout";
    public static final String POLICY_RETRY_LATENCY = "policy.retry.latency";


    public static final String USERNAME = "cfClientId";
    public static final String PASSWORD = "cfClientSecretBase64Encoded";
    public static final String CFURL = "cfUrl";

    public static final String SUBJECT_DEPLOYMENT = "subject.deployment";
    public static final String NATS_PORT = "nats.port";
    public static final String NATS_HOST = "nats.host";
    public static final String NATS_USER = "nats.user";
    public static final String NATS_PASS = "nats.pass";
    public static final String WAITTIME_DEPLOYMENT = "waittime.deployment";

    public static final String GUID = "guid";
    public static final String RESOURCES = "resources";
    public static final String ENTITY = "entity";
    public static final String METADATA = "metadata";
    public static final String NAME = "name";
    public static final String METRICS_PROCESSORS = "metrics.processors";
    public static final String METRICS_ACTIONS = "metrics.actions";

    public static final String POLLING_INTERVAL = "reportInterval";
    public static final String POLLING_WAIT = "pollingWaitbeforestart";
    public static final String POLLER_THREAD_COUNT = "pollerThreadCount";

    public static final String APP_TYPE_JAVA = "java";
    public static final String APP_TYPE_RUBY = "ruby";
    public static final String APP_TYPE_RUBY_SINATRA = "ruby_sinatra";
    public static final String APP_TYPE_RUBY_ON_RAILS = "ruby_on_rails";
    public static final String APP_TYPE_NODEJS = "nodejs";
    public static final String APP_TYPE_GO = "go";
    public static final String APP_TYPE_PHP = "php";
    public static final String APP_TYPE_PYTHON = "python";
    public static final String APP_TYPE_DOTNET = "dotnet";
    public static final String APP_TYPE_UNKNOWN = "unknown";
    public static final String APP_TYPE_DEFAULT = "default";

    public static final String DISPLAY_APP_TYPE_JAVA = "LIBERTY FOR JAVA";
    public static final String DISPLAY_APP_TYPE_RUBY = "Ruby";
    public static final String DISPLAY_APP_TYPE_RUBY_SINATRA = "Ruby_sinatra";
    public static final String DISPLAY_APP_TYPE_RUBY_ON_RAILS = "Ruby_on_rails";
    public static final String DISPLAY_APP_TYPE_NODEJS = "SDK FOR NODE.JS";
    public static final String DISPLAY_APP_TYPE_GO = "Go";
    public static final String DISPLAY_APP_TYPE_PHP = "PHP";
    public static final String DISPLAY_APP_TYPE_PYTHON = "Python";
    public static final String DISPLAY_APP_TYPE_DOTNET = ".NET";
    public static final String DISPLAY_APP_TYPE_DEFAULT = "default";

    //metric.* related entries defined in config.properties
	public static final String METRIC_WILDCARD = "*";
	public static final String METRIC_SEPERATOR = "_";
	public static final String METRIC_PREFIX = "metric";
	public static final String METRIC_SOURCE = "source";
	public static final String METRIC_SOURCE_AGENT = "agent";
	public static final String METRIC_SOURCE_POLLER = "poller";
	public static final String METRIC_SOURCE_STATUS = "enabled";
	public static final String METRIC_SOURCE_TYPE = "type";
	public static final String REPORT_INTERVAL = "reportInterval";
	public static final String PERSIST_TIME = "persistTimeInDb";
	public static final String METRICS_CONFIG = "metricsConfig";

	//cf state of an app, the valid value is "started" and "stopped"
	public static final String CF_APPLICATION_STATE_STARTED = "STARTED";
	public static final String CF_APPLICATION_STATE_STOPPED = "STOPPED";

	//Policy data range
	public static final String[] timezones = new String[] {
	           "(GMT -12:00) Etc/GMT+12",
	           "(GMT -11:00) Etc/GMT+11",
	           "(GMT -11:00) Pacific/Midway",
	           "(GMT -11:00) Pacific/Niue",
	           "(GMT -11:00) Pacific/Pago_Pago",
	           "(GMT -11:00) Pacific/Samoa",
	           "(GMT -11:00) US/Samoa",
	           "(GMT -10:00) Etc/GMT+10",
	           "(GMT -10:00) HST",
	           "(GMT -10:00) Pacific/Honolulu",
	           "(GMT -10:00) Pacific/Johnston",
	           "(GMT -10:00) Pacific/Rarotonga",
	           "(GMT -10:00) Pacific/Tahiti",
	           "(GMT -10:00) US/Hawaii",
	           "(GMT -09:30) Pacific/Marquesas",
	           "(GMT -09:00) America/Adak",
	           "(GMT -09:00) America/Atka",
	           "(GMT -09:00) Etc/GMT+9",
	           "(GMT -09:00) Pacific/Gambier",
	           "(GMT -09:00) US/Aleutian",
	           "(GMT -08:00) America/Anchorage",
	           "(GMT -08:00) America/Juneau",
	           "(GMT -08:00) America/Metlakatla",
	           "(GMT -08:00) America/Nome",
	           "(GMT -08:00) America/Sitka",
	           "(GMT -08:00) America/Yakutat",
	           "(GMT -08:00) Etc/GMT+8",
	           "(GMT -08:00) Pacific/Pitcairn",
	           "(GMT -08:00) US/Alaska",
	           "(GMT -07:00) America/Creston",
	           "(GMT -07:00) America/Dawson",
	           "(GMT -07:00) America/Dawson_Creek",
	           "(GMT -07:00) America/Ensenada",
	           "(GMT -07:00) America/Hermosillo",
	           "(GMT -07:00) America/Los_Angeles",
	           "(GMT -07:00) America/Phoenix",
	           "(GMT -07:00) America/Santa_Isabel",
	           "(GMT -07:00) America/Tijuana",
	           "(GMT -07:00) America/Vancouver",
	           "(GMT -07:00) America/Whitehorse",
	           "(GMT -07:00) Canada/Pacific",
	           "(GMT -07:00) Canada/Yukon",
	           "(GMT -07:00) Etc/GMT+7",
	           "(GMT -07:00) MST",
	           "(GMT -07:00) Mexico/BajaNorte",
	           "(GMT -07:00) PST8PDT",
	           "(GMT -07:00) US/Arizona",
	           "(GMT -07:00) US/Pacific",
	           "(GMT -07:00) US/Pacific-New",
	           "(GMT -06:00) America/Belize",
	           "(GMT -06:00) America/Boise",
	           "(GMT -06:00) America/Cambridge_Bay",
	           "(GMT -06:00) America/Chihuahua",
	           "(GMT -06:00) America/Costa_Rica",
	           "(GMT -06:00) America/Denver",
	           "(GMT -06:00) America/Edmonton",
	           "(GMT -06:00) America/El_Salvador",
	           "(GMT -06:00) America/Guatemala",
	           "(GMT -06:00) America/Inuvik",
	           "(GMT -06:00) America/Managua",
	           "(GMT -06:00) America/Mazatlan",
	           "(GMT -06:00) America/Ojinaga",
	           "(GMT -06:00) America/Regina",
	           "(GMT -06:00) America/Shiprock",
	           "(GMT -06:00) America/Swift_Current",
	           "(GMT -06:00) America/Tegucigalpa",
	           "(GMT -06:00) America/Yellowknife",
	           "(GMT -06:00) Canada/East-Saskatchewan",
	           "(GMT -06:00) Canada/Mountain",
	           "(GMT -06:00) Canada/Saskatchewan",
	           "(GMT -06:00) Etc/GMT+6",
	           "(GMT -06:00) MST7MDT",
	           "(GMT -06:00) Mexico/BajaSur",
	           "(GMT -06:00) Navajo",
	           "(GMT -06:00) Pacific/Galapagos",
	           "(GMT -06:00) US/Mountain",
	           "(GMT -05:00) America/Atikokan",
	           "(GMT -05:00) America/Bahia_Banderas",
	           "(GMT -05:00) America/Bogota",
	           "(GMT -05:00) America/Cancun",
	           "(GMT -05:00) America/Cayman",
	           "(GMT -05:00) America/Chicago",
	           "(GMT -05:00) America/Coral_Harbour",
	           "(GMT -05:00) America/Eirunepe",
	           "(GMT -05:00) America/Guayaquil",
	           "(GMT -05:00) America/Indiana/Knox",
	           "(GMT -05:00) America/Indiana/Tell_City",
	           "(GMT -05:00) America/Jamaica",
	           "(GMT -05:00) America/Knox_IN",
	           "(GMT -05:00) America/Lima",
	           "(GMT -05:00) America/Matamoros",
	           "(GMT -05:00) America/Menominee",
	           "(GMT -05:00) America/Merida",
	           "(GMT -05:00) America/Mexico_City",
	           "(GMT -05:00) America/Monterrey",
	           "(GMT -05:00) America/North_Dakota/Beulah",
	           "(GMT -05:00) America/North_Dakota/Center",
	           "(GMT -05:00) America/North_Dakota/New_Salem",
	           "(GMT -05:00) America/Panama",
	           "(GMT -05:00) America/Porto_Acre",
	           "(GMT -05:00) America/Rainy_River",
	           "(GMT -05:00) America/Rankin_Inlet",
	           "(GMT -05:00) America/Resolute",
	           "(GMT -05:00) America/Rio_Branco",
	           "(GMT -05:00) America/Winnipeg",
	           "(GMT -05:00) Brazil/Acre",
	           "(GMT -05:00) CST6CDT",
	           "(GMT -05:00) Canada/Central",
	           "(GMT -05:00) Chile/EasterIsland",
	           "(GMT -05:00) EST",
	           "(GMT -05:00) Etc/GMT+5",
	           "(GMT -05:00) Jamaica",
	           "(GMT -05:00) Mexico/General",
	           "(GMT -05:00) Pacific/Easter",
	           "(GMT -05:00) US/Central",
	           "(GMT -05:00) US/Indiana-Starke",
	           "(GMT -04:30) America/Caracas",
	           "(GMT -04:00) America/Anguilla",
	           "(GMT -04:00) America/Antigua",
	           "(GMT -04:00) America/Aruba",
	           "(GMT -04:00) America/Asuncion",
	           "(GMT -04:00) America/Barbados",
	           "(GMT -04:00) America/Blanc-Sablon",
	           "(GMT -04:00) America/Boa_Vista",
	           "(GMT -04:00) America/Campo_Grande",
	           "(GMT -04:00) America/Cuiaba",
	           "(GMT -04:00) America/Curacao",
	           "(GMT -04:00) America/Detroit",
	           "(GMT -04:00) America/Dominica",
	           "(GMT -04:00) America/Fort_Wayne",
	           "(GMT -04:00) America/Grand_Turk",
	           "(GMT -04:00) America/Grenada",
	           "(GMT -04:00) America/Guadeloupe",
	           "(GMT -04:00) America/Guyana",
	           "(GMT -04:00) America/Havana",
	           "(GMT -04:00) America/Indiana/Indianapolis",
	           "(GMT -04:00) America/Indiana/Marengo",
	           "(GMT -04:00) America/Indiana/Petersburg",
	           "(GMT -04:00) America/Indiana/Vevay",
	           "(GMT -04:00) America/Indiana/Vincennes",
	           "(GMT -04:00) America/Indiana/Winamac",
	           "(GMT -04:00) America/Indianapolis",
	           "(GMT -04:00) America/Iqaluit ",
	           "(GMT -04:00) America/Kentucky/Louisville ",
	           "(GMT -04:00) America/Kentucky/Monticello",
	           "(GMT -04:00) America/Kralendijk",
	           "(GMT -04:00) America/La_Paz",
	           "(GMT -04:00) America/Louisville ",
	           "(GMT -04:00) America/Lower_Princes",
	           "(GMT -04:00) America/Manaus",
	           "(GMT -04:00) America/Marigot",
	           "(GMT -04:00) America/Martinique",
	           "(GMT -04:00) America/Montreal",
	           "(GMT -04:00) America/Montserrat",
	           "(GMT -04:00) America/Nassau",
	           "(GMT -04:00) America/New_York",
	           "(GMT -04:00) America/Nipigon",
	           "(GMT -04:00) America/Pangnirtung ",
	           "(GMT -04:00) America/Port-au-Prince ",
	           "(GMT -04:00) America/Port_of_Spain",
	           "(GMT -04:00) America/Porto_Velho",
	           "(GMT -04:00) America/Puerto_Rico ",
	           "(GMT -04:00) America/Santo_Domingo ",
	           "(GMT -04:00) America/St_Barthelemy",
	           "(GMT -04:00) America/St_Kitts",
	           "(GMT -04:00) America/St_Lucia",
	           "(GMT -04:00) America/St_Thomas",
	           "(GMT -04:00) America/St_Vincent",
	           "(GMT -04:00) America/Thunder_Bay",
	           "(GMT -04:00) America/Toronto",
	           "(GMT -04:00) America/Tortola",
	           "(GMT -04:00) America/Virgin",
	           "(GMT -04:00) Brazil/West",
	           "(GMT -04:00) Canada/Eastern",
	           "(GMT -04:00) Cuba",
	           "(GMT -04:00) EST5EDT",
	           "(GMT -04:00) Etc/GMT+4",
	           "(GMT -04:00) US/East-Indiana",
	           "(GMT -04:00) US/Eastern",
	           "(GMT -04:00) US/Michigan",
	           "(GMT -03:00) America/Araguaina ",
	           "(GMT -03:00) America/Argentina/Buenos_Aires ",
	           "(GMT -03:00) America/Argentina/Catamarca ",
	           "(GMT -03:00) America/Argentina/ComodRivadavia ",
	           "(GMT -03:00) America/Argentina/Cordoba ",
	           "(GMT -03:00) America/Argentina/Jujuy ",
	           "(GMT -03:00) America/Argentina/La_Rioja ",
	           "(GMT -03:00) America/Argentina/Mendoza ",
	           "(GMT -03:00) America/Argentina/Rio_Gallegos ",
	           "(GMT -03:00) America/Argentina/Salta ",
	           "(GMT -03:00) America/Argentina/San_Juan ",
	           "(GMT -03:00) America/Argentina/San_Luis ",
	           "(GMT -03:00) America/Argentina/Tucuman ",
	           "(GMT -03:00) America/Argentina/Ushuaia",
	           "(GMT -03:00) America/Bahia",
	           "(GMT -03:00) America/Belem",
	           "(GMT -03:00) America/Buenos_Aires",
	           "(GMT -03:00) America/Catamarca",
	           "(GMT -03:00) America/Cayenne",
	           "(GMT -03:00) America/Cordoba",
	           "(GMT -03:00) America/Fortaleza",
	           "(GMT -03:00) America/Glace_Bay",
	           "(GMT -03:00) America/Goose_Bay",
	           "(GMT -03:00) America/Halifax",
	           "(GMT -03:00) America/Jujuy",
	           "(GMT -03:00) America/Maceio",
	           "(GMT -03:00) America/Mendoza",
	           "(GMT -03:00) America/Moncton",
	           "(GMT -03:00) America/Montevideo",
	           "(GMT -03:00) America/Paramaribo",
	           "(GMT -03:00) America/Recife",
	           "(GMT -03:00) America/Rosario",
	           "(GMT -03:00) America/Santarem",
	           "(GMT -03:00) America/Santiago",
	           "(GMT -03:00) America/Sao_Paulo",
	           "(GMT -03:00) America/Thule",
	           "(GMT -03:00) Antarctica/Palmer",
	           "(GMT -03:00) Antarctica/Rothera",
	           "(GMT -03:00) Atlantic/Bermuda",
	           "(GMT -03:00) Atlantic/Stanley",
	           "(GMT -03:00) Brazil/East",
	           "(GMT -03:00) Canada/Atlantic",
	           "(GMT -03:00) Chile/Continental",
	           "(GMT -03:00) Etc/GMT+3",
	           "(GMT -02:30) America/St_Johns",
	           "(GMT -02:30) Canada/Newfoundland",
	           "(GMT -02:00) America/Godthab",
	           "(GMT -02:00) America/Miquelon",
	           "(GMT -02:00) America/Noronha ",
	           "(GMT -02:00) Atlantic/South_Georgia",
	           "(GMT -02:00) Brazil/DeNoronha",
	           "(GMT -02:00) Etc/GMT+2",
	           "(GMT -01:00) Atlantic/Cape_Verde",
	           "(GMT -01:00) Etc/GMT+1",
	           "(GMT +00:00) Africa/Abidjan",
	           "(GMT +00:00) Africa/Accra",
	           "(GMT +00:00) Africa/Bamako",
	           "(GMT +00:00) Africa/Banjul",
	           "(GMT +00:00) Africa/Bissau",
	           "(GMT +00:00) Africa/Conakry",
	           "(GMT +00:00) Africa/Dakar",
	           "(GMT +00:00) Africa/Freetown",
	           "(GMT +00:00) Africa/Lome",
	           "(GMT +00:00) Africa/Monrovia",
	           "(GMT +00:00) Africa/Nouakchott",
	           "(GMT +00:00) Africa/Ouagadougou",
	           "(GMT +00:00) Africa/Sao_Tome",
	           "(GMT +00:00) Africa/Timbuktu",
	           "(GMT +00:00) America/Danmarkshavn",
	           "(GMT +00:00) America/Scoresbysund",
	           "(GMT +00:00) Atlantic/Azores",
	           "(GMT +00:00) Atlantic/Reykjavik",
	           "(GMT +00:00) Atlantic/St_Helena",
	           "(GMT +00:00) Etc/GMT",
	           "(GMT +00:00) Etc/GMT+0",
	           "(GMT +00:00) Etc/GMT-0",
	           "(GMT +00:00) Etc/GMT0",
	           "(GMT +00:00) Etc/Greenwich",
	           "(GMT +00:00) Etc/UCT",
	           "(GMT +00:00) Etc/UTC",
	           "(GMT +00:00) Etc/Universal",
	           "(GMT +00:00) Etc/Zulu",
	           "(GMT +00:00) GMT",
	           "(GMT +00:00) GMT+0",
	           "(GMT +00:00) GMT-0",
	           "(GMT +00:00) GMT0",
	           "(GMT +00:00) Greenwich",
	           "(GMT +00:00) Iceland",
	           "(GMT +00:00) UCT",
	           "(GMT +00:00) UTC",
	           "(GMT +00:00) Universal",
	           "(GMT +00:00) Zulu",
	           "(GMT +01:00) Africa/Algiers",
	           "(GMT +01:00) Africa/Bangui",
	           "(GMT +01:00) Africa/Brazzaville",
	           "(GMT +01:00) Africa/Casablanca",
	           "(GMT +01:00) Africa/Douala",
	           "(GMT +01:00) Africa/El_Aaiun",
	           "(GMT +01:00) Africa/Kinshasa",
	           "(GMT +01:00) Africa/Lagos",
	           "(GMT +01:00) Africa/Libreville",
	           "(GMT +01:00) Africa/Luanda",
	           "(GMT +01:00) Africa/Malabo",
	           "(GMT +01:00) Africa/Ndjamena",
	           "(GMT +01:00) Africa/Niamey",
	           "(GMT +01:00) Africa/Porto-Novo",
	           "(GMT +01:00) Africa/Tunis",
	           "(GMT +01:00) Africa/Windhoek",
	           "(GMT +01:00) Atlantic/Canary",
	           "(GMT +01:00) Atlantic/Faeroe",
	           "(GMT +01:00) Atlantic/Faroe",
	           "(GMT +01:00) Atlantic/Madeira",
	           "(GMT +01:00) Eire",
	           "(GMT +01:00) Etc/GMT-1",
	           "(GMT +01:00) Europe/Belfast",
	           "(GMT +01:00) Europe/Dublin",
	           "(GMT +01:00) Europe/Guernsey",
	           "(GMT +01:00) Europe/Isle_of_Man",
	           "(GMT +01:00) Europe/Jersey",
	           "(GMT +01:00) Europe/Lisbon",
	           "(GMT +01:00) Europe/London",
	           "(GMT +01:00) GB",
	           "(GMT +01:00) GB-Eire",
	           "(GMT +01:00) Portugal",
	           "(GMT +01:00) WET",
	           "(GMT +02:00) Africa/Blantyre",
	           "(GMT +02:00) Africa/Bujumbura",
	           "(GMT +02:00) Africa/Cairo",
	           "(GMT +02:00) Africa/Ceuta",
	           "(GMT +02:00) Africa/Gaborone",
	           "(GMT +02:00) Africa/Harare",
	           "(GMT +02:00) Africa/Johannesburg",
	           "(GMT +02:00) Africa/Kigali",
	           "(GMT +02:00) Africa/Lubumbashi",
	           "(GMT +02:00) Africa/Lusaka",
	           "(GMT +02:00) Africa/Maputo",
	           "(GMT +02:00) Africa/Maseru",
	           "(GMT +02:00) Africa/Mbabane",
	           "(GMT +02:00) Africa/Tripoli",
	           "(GMT +02:00) Antarctica/Troll",
	           "(GMT +02:00) Arctic/Longyearbyen",
	           "(GMT +02:00) Atlantic/Jan_Mayen",
	           "(GMT +02:00) CET",
	           "(GMT +02:00) Egypt",
	           "(GMT +02:00) Etc/GMT-2",
	           "(GMT +02:00) Europe/Amsterdam",
	           "(GMT +02:00) Europe/Andorra",
	           "(GMT +02:00) Europe/Belgrade",
	           "(GMT +02:00) Europe/Berlin",
	           "(GMT +02:00) Europe/Bratislava",
	           "(GMT +02:00) Europe/Brussels",
	           "(GMT +02:00) Europe/Budapest",
	           "(GMT +02:00) Europe/Busingen",
	           "(GMT +02:00) Europe/Copenhagen",
	           "(GMT +02:00) Europe/Gibraltar",
	           "(GMT +02:00) Europe/Kaliningrad",
	           "(GMT +02:00) Europe/Ljubljana",
	           "(GMT +02:00) Europe/Luxembourg",
	           "(GMT +02:00) Europe/Madrid",
	           "(GMT +02:00) Europe/Malta",
	           "(GMT +02:00) Europe/Monaco",
	           "(GMT +02:00) Europe/Oslo",
	           "(GMT +02:00) Europe/Paris",
	           "(GMT +02:00) Europe/Podgorica",
	           "(GMT +02:00) Europe/Prague",
	           "(GMT +02:00) Europe/Rome",
	           "(GMT +02:00) Europe/San_Marino",
	           "(GMT +02:00) Europe/Sarajevo",
	           "(GMT +02:00) Europe/Skopje",
	           "(GMT +02:00) Europe/Stockholm",
	           "(GMT +02:00) Europe/Tirane",
	           "(GMT +02:00) Europe/Vaduz",
	           "(GMT +02:00) Europe/Vatican",
	           "(GMT +02:00) Europe/Vienna",
	           "(GMT +02:00) Europe/Warsaw",
	           "(GMT +02:00) Europe/Zagreb",
	           "(GMT +02:00) Europe/Zurich",
	           "(GMT +02:00) Libya",
	           "(GMT +02:00) MET",
	           "(GMT +02:00) Poland",
	           "(GMT +03:00) Africa/Addis_Ababa",
	           "(GMT +03:00) Africa/Asmara",
	           "(GMT +03:00) Africa/Asmera",
	           "(GMT +03:00) Africa/Dar_es_Salaam",
	           "(GMT +03:00) Africa/Djibouti",
	           "(GMT +03:00) Africa/Juba",
	           "(GMT +03:00) Africa/Kampala",
	           "(GMT +03:00) Africa/Khartoum",
	           "(GMT +03:00) Africa/Mogadishu",
	           "(GMT +03:00) Africa/Nairobi",
	           "(GMT +03:00) Antarctica/Syowa",
	           "(GMT +03:00) Asia/Aden",
	           "(GMT +03:00) Asia/Amman",
	           "(GMT +03:00) Asia/Baghdad",
	           "(GMT +03:00) Asia/Bahrain",
	           "(GMT +03:00) Asia/Beirut",
	           "(GMT +03:00) Asia/Damascus",
	           "(GMT +03:00) Asia/Gaza",
	           "(GMT +03:00) Asia/Hebron",
	           "(GMT +03:00) Asia/Istanbul",
	           "(GMT +03:00) Asia/Jerusalem",
	           "(GMT +03:00) Asia/Kuwait",
	           "(GMT +03:00) Asia/Nicosia",
	           "(GMT +03:00) Asia/Qatar",
	           "(GMT +03:00) Asia/Riyadh",
	           "(GMT +03:00) Asia/Tel_Aviv",
	           "(GMT +03:00) EET",
	           "(GMT +03:00) Etc/GMT-3",
	           "(GMT +03:00) Europe/Athens",
	           "(GMT +03:00) Europe/Bucharest",
	           "(GMT +03:00) Europe/Chisinau",
	           "(GMT +03:00) Europe/Helsinki",
	           "(GMT +03:00) Europe/Istanbul",
	           "(GMT +03:00) Europe/Kiev",
	           "(GMT +03:00) Europe/Mariehamn",
	           "(GMT +03:00) Europe/Minsk",
	           "(GMT +03:00) Europe/Moscow",
	           "(GMT +03:00) Europe/Nicosia",
	           "(GMT +03:00) Europe/Riga",
	           "(GMT +03:00) Europe/Simferopol",
	           "(GMT +03:00) Europe/Sofia",
	           "(GMT +03:00) Europe/Tallinn",
	           "(GMT +03:00) Europe/Tiraspol",
	           "(GMT +03:00) Europe/Uzhgorod",
	           "(GMT +03:00) Europe/Vilnius",
	           "(GMT +03:00) Europe/Volgograd",
	           "(GMT +03:00) Europe/Zaporozhye",
	           "(GMT +03:00) Indian/Antananarivo",
	           "(GMT +03:00) Indian/Comoro",
	           "(GMT +03:00) Indian/Mayotte",
	           "(GMT +03:00) Israel",
	           "(GMT +03:00) Turkey",
	           "(GMT +03:00) W-SU",
	           "(GMT +04:00) Asia/Dubai",
	           "(GMT +04:00) Asia/Muscat",
	           "(GMT +04:00) Asia/Tbilisi",
	           "(GMT +04:00) Asia/Yerevan",
	           "(GMT +04:00) Etc/GMT-4",
	           "(GMT +04:00) Europe/Samara",
	           "(GMT +04:00) Indian/Mahe",
	           "(GMT +04:00) Indian/Mauritius",
	           "(GMT +04:00) Indian/Reunion",
	           "(GMT +04:30) Asia/Kabul",
	           "(GMT +04:30) Asia/Tehran",
	           "(GMT +04:30) Iran",
	           "(GMT +05:00) Antarctica/Mawson",
	           "(GMT +05:00) Asia/Aqtau",
	           "(GMT +05:00) Asia/Aqtobe",
	           "(GMT +05:00) Asia/Ashgabat",
	           "(GMT +05:00) Asia/Ashkhabad",
	           "(GMT +05:00) Asia/Baku",
	           "(GMT +05:00) Asia/Dushanbe",
	           "(GMT +05:00) Asia/Karachi",
	           "(GMT +05:00) Asia/Oral",
	           "(GMT +05:00) Asia/Samarkand",
	           "(GMT +05:00) Asia/Tashkent",
	           "(GMT +05:00) Asia/Yekaterinburg",
	           "(GMT +05:00) Etc/GMT-5",
	           "(GMT +05:00) Indian/Kerguelen",
	           "(GMT +05:00) Indian/Maldives",
	           "(GMT +05:30) Asia/Calcutta",
	           "(GMT +05:30) Asia/Colombo",
	           "(GMT +05:30) Asia/Kolkata",
	           "(GMT +05:45) Asia/Kathmandu",
	           "(GMT +05:45) Asia/Katmandu",
	           "(GMT +06:00) Antarctica/Vostok",
	           "(GMT +06:00) Asia/Almaty",
	           "(GMT +06:00) Asia/Bishkek",
	           "(GMT +06:00) Asia/Dacca",
	           "(GMT +06:00) Asia/Dhaka",
	           "(GMT +06:00) Asia/Kashgar",
	           "(GMT +06:00) Asia/Novosibirsk",
	           "(GMT +06:00) Asia/Omsk",
	           "(GMT +06:00) Asia/Qyzylorda",
	           "(GMT +06:00) Asia/Thimbu",
	           "(GMT +06:00) Asia/Thimphu",
	           "(GMT +06:00) Asia/Urumqi",
	           "(GMT +06:00) Etc/GMT-6",
	           "(GMT +06:00) Indian/Chagos",
	           "(GMT +06:30) Asia/Rangoon",
	           "(GMT +06:30) Indian/Cocos",
	           "(GMT +07:00) Antarctica/Davis",
	           "(GMT +07:00) Asia/Bangkok",
	           "(GMT +07:00) Asia/Ho_Chi_Minh",
	           "(GMT +07:00) Asia/Hovd",
	           "(GMT +07:00) Asia/Jakarta",
	           "(GMT +07:00) Asia/Krasnoyarsk",
	           "(GMT +07:00) Asia/Novokuznetsk",
	           "(GMT +07:00) Asia/Phnom_Penh",
	           "(GMT +07:00) Asia/Pontianak",
	           "(GMT +07:00) Asia/Saigon",
	           "(GMT +07:00) Asia/Vientiane",
	           "(GMT +07:00) Etc/GMT-7",
	           "(GMT +07:00) Indian/Christmas",
	           "(GMT +08:00) Antarctica/Casey",
	           "(GMT +08:00) Asia/Brunei",
	           "(GMT +08:00) Asia/Chita",
	           "(GMT +08:00) Asia/Choibalsan",
	           "(GMT +08:00) Asia/Chongqing",
	           "(GMT +08:00) Asia/Chungking",
	           "(GMT +08:00) Asia/Harbin",
	           "(GMT +08:00) Asia/Hong_Kong",
	           "(GMT +08:00) Asia/Irkutsk",
	           "(GMT +08:00) Asia/Kuala_Lumpur",
	           "(GMT +08:00) Asia/Kuching",
	           "(GMT +08:00) Asia/Macao",
	           "(GMT +08:00) Asia/Macau",
	           "(GMT +08:00) Asia/Makassar",
	           "(GMT +08:00) Asia/Manila",
	           "(GMT +08:00) Asia/Shanghai",
	           "(GMT +08:00) Asia/Singapore",
	           "(GMT +08:00) Asia/Taipei",
	           "(GMT +08:00) Asia/Ujung_Pandang",
	           "(GMT +08:00) Asia/Ulaanbaatar",
	           "(GMT +08:00) Asia/Ulan_Bator",
	           "(GMT +08:00) Australia/Perth",
	           "(GMT +08:00) Australia/West",
	           "(GMT +08:00) Etc/GMT-8",
	           "(GMT +08:00) Hongkong",
	           "(GMT +08:00) PRC",
	           "(GMT +08:00) ROC",
	           "(GMT +08:00) Singapore",
	           "(GMT +08:45) Australia/Eucla",
	           "(GMT +09:00) Asia/Dili",
	           "(GMT +09:00) Asia/Jayapura",
	           "(GMT +09:00) Asia/Khandyga",
	           "(GMT +09:00) Asia/Pyongyang",
	           "(GMT +09:00) Asia/Seoul",
	           "(GMT +09:00) Asia/Tokyo",
	           "(GMT +09:00) Asia/Yakutsk",
	           "(GMT +09:00) Etc/GMT-9",
	           "(GMT +09:00) Japan",
	           "(GMT +09:00) Pacific/Palau",
	           "(GMT +09:00) ROK",
	           "(GMT +09:30) Australia/Adelaide ",
	           "(GMT +09:30) Australia/Broken_Hill",
	           "(GMT +09:30) Australia/Darwin",
	           "(GMT +09:30) Australia/North",
	           "(GMT +09:30) Australia/South",
	           "(GMT +09:30) Australia/Yancowinna ",
	           "(GMT +10:00) Antarctica/DumontDUrville",
	           "(GMT +10:00) Asia/Magadan",
	           "(GMT +10:00) Asia/Sakhalin",
	           "(GMT +10:00) Asia/Ust-Nera",
	           "(GMT +10:00) Asia/Vladivostok",
	           "(GMT +10:00) Australia/ACT",
	           "(GMT +10:00) Australia/Brisbane",
	           "(GMT +10:00) Australia/Canberra",
	           "(GMT +10:00) Australia/Currie",
	           "(GMT +10:00) Australia/Hobart",
	           "(GMT +10:00) Australia/Lindeman",
	           "(GMT +10:00) Australia/Melbourne",
	           "(GMT +10:00) Australia/NSW",
	           "(GMT +10:00) Australia/Queensland",
	           "(GMT +10:00) Australia/Sydney",
	           "(GMT +10:00) Australia/Tasmania",
	           "(GMT +10:00) Australia/Victoria",
	           "(GMT +10:00) Etc/GMT-10",
	           "(GMT +10:00) Pacific/Chuuk",
	           "(GMT +10:00) Pacific/Guam",
	           "(GMT +10:00) Pacific/Port_Moresby",
	           "(GMT +10:00) Pacific/Saipan",
	           "(GMT +10:00) Pacific/Truk",
	           "(GMT +10:00) Pacific/Yap",
	           "(GMT +10:30) Australia/LHI",
	           "(GMT +10:30) Australia/Lord_Howe",
	           "(GMT +11:00) Antarctica/Macquarie",
	           "(GMT +11:00) Asia/Srednekolymsk",
	           "(GMT +11:00) Etc/GMT-11",
	           "(GMT +11:00) Pacific/Bougainville",
	           "(GMT +11:00) Pacific/Efate",
	           "(GMT +11:00) Pacific/Guadalcanal",
	           "(GMT +11:00) Pacific/Kosrae",
	           "(GMT +11:00) Pacific/Noumea",
	           "(GMT +11:00) Pacific/Pohnpei",
	           "(GMT +11:00) Pacific/Ponape",
	           "(GMT +11:30) Pacific/Norfolk",
	           "(GMT +12:00) Antarctica/McMurdo",
	           "(GMT +12:00) Antarctica/South_Pole",
	           "(GMT +12:00) Asia/Anadyr",
	           "(GMT +12:00) Asia/Kamchatka",
	           "(GMT +12:00) Etc/GMT-12",
	           "(GMT +12:00) Kwajalein",
	           "(GMT +12:00) NZ",
	           "(GMT +12:00) Pacific/Auckland",
	           "(GMT +12:00) Pacific/Fiji",
	           "(GMT +12:00) Pacific/Funafuti",
	           "(GMT +12:00) Pacific/Kwajalein",
	           "(GMT +12:00) Pacific/Majuro",
	           "(GMT +12:00) Pacific/Nauru",
	           "(GMT +12:00) Pacific/Tarawa",
	           "(GMT +12:00) Pacific/Wake",
	           "(GMT +12:00) Pacific/Wallis",
	           "(GMT +12:45) NZ-CHAT",
	           "(GMT +12:45) Pacific/Chatham",
	           "(GMT +13:00) Etc/GMT-13",
	           "(GMT +13:00) Pacific/Apia",
	           "(GMT +13:00) Pacific/Enderbury",
	           "(GMT +13:00) Pacific/Fakaofo",
	           "(GMT +13:00) Pacific/Tongatapu",
	           "(GMT +14:00) Etc/GMT-14",
	           "(GMT +14:00) Pacific/Kiritimati"};

	private static final String CLASS_NAME = Constants.class.getName();
	private static final Logger logger     = Logger.getLogger(CLASS_NAME);
	public static Map<String, String> appType_Display_mapping = new HashMap<String, String>() //server metric string to metricType
	{
	    {
            put(APP_TYPE_JAVA, DISPLAY_APP_TYPE_JAVA);
            put(APP_TYPE_RUBY, DISPLAY_APP_TYPE_RUBY);
            put(APP_TYPE_RUBY_SINATRA, DISPLAY_APP_TYPE_RUBY_SINATRA);
            put(APP_TYPE_RUBY_ON_RAILS, DISPLAY_APP_TYPE_RUBY_ON_RAILS);
            put(APP_TYPE_NODEJS, DISPLAY_APP_TYPE_NODEJS);
            put(APP_TYPE_GO, DISPLAY_APP_TYPE_GO);
            put(APP_TYPE_PHP, DISPLAY_APP_TYPE_PHP);
            put(APP_TYPE_PYTHON, DISPLAY_APP_TYPE_PYTHON);
            put(APP_TYPE_DOTNET, DISPLAY_APP_TYPE_DOTNET);
            put(APP_TYPE_DEFAULT, DISPLAY_APP_TYPE_DEFAULT);
	    }
	};
	public static Map<String, String> Display_appType_mapping = new HashMap<String, String>() //server metric string to metricType
	{
	    {
            put(DISPLAY_APP_TYPE_JAVA, APP_TYPE_JAVA);
            put(DISPLAY_APP_TYPE_RUBY, APP_TYPE_RUBY);
            put(DISPLAY_APP_TYPE_RUBY_SINATRA, APP_TYPE_RUBY_SINATRA);
            put(DISPLAY_APP_TYPE_RUBY_ON_RAILS, APP_TYPE_RUBY_ON_RAILS);
            put(DISPLAY_APP_TYPE_NODEJS, APP_TYPE_NODEJS);
            put(DISPLAY_APP_TYPE_GO, APP_TYPE_GO);
            put(DISPLAY_APP_TYPE_PHP, APP_TYPE_PHP);
            put(DISPLAY_APP_TYPE_PYTHON, APP_TYPE_PYTHON);
            put(DISPLAY_APP_TYPE_DOTNET, APP_TYPE_DOTNET);
            put(DISPLAY_APP_TYPE_DEFAULT, APP_TYPE_DEFAULT);
	    }
	};
	public static String INTERNAL_METRIC_TYPE_JVMHEAPUSAGE = "HeapMemory";
	public static String INTERNAL_METRIC_TYPE_MEMORY = "Memory";
	public static String INTERNAL_METRIC_TYPE_THROUGHPUT = "Throughput";
	public static String INTERNAL_METRIC_TYPE_RESPONSETIME = "ResponseTime";
	public static String INTERNAL_METRIC_TYPE_EVENTLOOPLATENCY = "EventLoopLatency";
	public static String INTERNAL_METRIC_TYPE_NODEJSHEAPMEMORY = "NODEJSHeapMemory";
	public static String METRIC_TYPE_JVMHEAPUSAGE = "JVMHeapUsage";
	public static String METRIC_TYPE_MEMORY = "Memory";
	public static String METRIC_TYPE_THROUGHPUT = "Throughput";
	public static String METRIC_TYPE_RESPONSETIME = "ResponseTime";
	public static String METRIC_TYPE_EVENTLOOPLATENCY = "EventLoopLatency";
	public static String METRIC_TYPE_HEAPUSAGE = "HeapUsage";
	public static String[] metrictype = {METRIC_TYPE_MEMORY};
	public static String[] APPTYPE={
		 APP_TYPE_JAVA, APP_TYPE_RUBY, APP_TYPE_RUBY_SINATRA, APP_TYPE_RUBY_ON_RAILS, APP_TYPE_NODEJS,
		 APP_TYPE_GO, APP_TYPE_PHP, APP_TYPE_PYTHON, APP_TYPE_DOTNET
	};
	public static String[] APP_TOBE_UPDATE={APP_TYPE_JAVA, APP_TYPE_NODEJS};
	public static String[] JAVA_METRICS = {METRIC_TYPE_MEMORY};
	public static String[] NODEJS_METRICS = {METRIC_TYPE_MEMORY};
	public static String[] DEFAULT_METRICS = {METRIC_TYPE_MEMORY};
	public static Map<String,String[]> appType_metric_mapping = new HashMap<String, String[]>()
	{
	    {
	        put(APP_TYPE_JAVA, JAVA_METRICS);
	        put(APP_TYPE_NODEJS, NODEJS_METRICS);
	        put(APP_TYPE_DEFAULT, DEFAULT_METRICS);
	    }
	};
	public static Map<String, String> metricType_mapping = new HashMap<String, String>() //server metric string to metricType
	{
	    {
	        put(INTERNAL_METRIC_TYPE_JVMHEAPUSAGE, METRIC_TYPE_JVMHEAPUSAGE);
	        put(INTERNAL_METRIC_TYPE_MEMORY, METRIC_TYPE_MEMORY);
	        put(INTERNAL_METRIC_TYPE_THROUGHPUT, METRIC_TYPE_THROUGHPUT);
	        put(INTERNAL_METRIC_TYPE_RESPONSETIME, METRIC_TYPE_RESPONSETIME);
	        put(INTERNAL_METRIC_TYPE_EVENTLOOPLATENCY, METRIC_TYPE_EVENTLOOPLATENCY);
	        put(INTERNAL_METRIC_TYPE_NODEJSHEAPMEMORY, METRIC_TYPE_HEAPUSAGE);
	    }
	};
	public static int REPORTINTERVAL = ConfigManager.getInt(Constants.REPORT_INTERVAL, 60);
	public static int value_metric_refresh_interval = REPORTINTERVAL * 1000;
	public static int value_min_statwindow = REPORTINTERVAL;
	public static int value_min_breachDuration = REPORTINTERVAL;
//	public static int value_min_cooldown = REPORTINTERVAL;
	public static int value_min_stepDownCoolDownSecs = REPORTINTERVAL;
	public static int value_min_stepUpCoolDownSecs = REPORTINTERVAL;
	public static int value_min_lowerThreshold_JVMHeapUsage = 1;
	public static String value_min_upperThreshold_JVMHeapUsage= "x"; //this for cmd line tool
	public static int value_min_lowerThreshold_Memory = 1;
	public static String value_min_upperThreshold_Memory= "x"; //this for cmd line tool
	public static int value_min_lowerThreshold_Throughput = 1;
	public static String value_min_upperThreshold_Throughput= "x"; //this for cmd line tool
	public static int value_min_lowerThreshold_ResponseTime = 1;
	public static String value_min_upperThreshold_ResponseTime= "x"; //this for cmd line tool
	public static int value_min_lowerThreshold_EventLoopLatency = 1;
	public static String value_min_upperThreshold_EventLoopLatency= "x"; //this for cmd line tool
	public static int value_min_lowerThreshold_HeapUsage = 1;
	public static String value_min_upperThreshold_HeapUsage= "x"; //this for cmd line tool
	public static int value_min_instanceStepCountDown = 1;
	public static int value_min_instanceStepCountUp = 1;
	public static int metricTimeRange = REPORTINTERVAL * 60 /60; //Given the unit of reportInterval is seconds, we need to /60 to get the value for unit "minutes". Then multiple with 60 as we need 60 points in the chart.
	public static int metricTimeRangeByMilliseconds = metricTimeRange * 60 * 1000;
	public static String value_nolimit = "nolimit";
	public static int value_default_statwindow = 300;
	public static int value_default_breachDuration = 600;
	//public static int value_default_cooldown = 600;
	public static int value_default_stepDownCoolDownSecs = 600;
	public static int value_default_stepUpCoolDownSecs = 600;
	public static int value_default_lowerThreshold_JVMHeapUsage = 30;
	public static int value_default_upperThreshold_JVMHeapUsage= 80;
	public static int value_default_lowerThreshold_Memory = 30;
	public static int value_default_upperThreshold_Memory= 80;
	public static int value_default_lowerThreshold_Throughput = 30;
	public static int value_default_upperThreshold_Throughput= 80;
	public static int value_default_lowerThreshold_ResponseTime = 30;
	public static int value_default_upperThreshold_ResponseTime= 80;
	public static int value_default_lowerThreshold_EventLoopLatency = 30;
	public static int value_default_upperThreshold_EventLoopLatency= 80;
	public static int value_default_lowerThreshold_HeapUsage = 30;
	public static int value_default_upperThreshold_HeapUsage= 80;
	public static int value_default_instanceStepCountDown = 1;
	public static int value_default_instanceStepCountUp = 1;
	public static int value_max_statwindow = 3600;
	public static int value_max_breachDuration = 3600;
//	public static int value_max_cooldown = 3600;
	public static int value_max_stepDownCoolDownSecs = 3600;
	public static int value_max_stepUpCoolDownSecs = 3600;
	public static int value_max_lowerThreshold_JVMHeapUsage = 100;
	public static int value_max_upperThreshold_JVMHeapUsage= 100;
	public static int value_max_lowerThreshold_Memory = 100;
	public static int value_max_upperThreshold_Memory= 100;
	public static String value_max_lowerThreshold_Throughput = "x";
	public static String value_max_upperThreshold_Throughput= "x";
	public static String value_max_lowerThreshold_ResponseTime = "x";
	public static String value_max_upperThreshold_ResponseTime= "x";
	public static String value_max_lowerThreshold_EventLoopLatency = "x";
	public static String value_max_upperThreshold_EventLoopLatency= "x";
	public static int value_max_lowerThreshold_HeapUsage = 100;
	public static int value_max_upperThreshold_HeapUsage= 100;
	public static String value_max_instanceStepCountDown = "x"; //this for cmd line tool
	public static String value_max_instanceStepCountUp = "x"; //this for cmd line tool
	public static String unit_statwindow = "seconds";
	public static String unit_breachDuration = "seconds";
	public static String unit_stepDownCoolDownSecs = "seconds";
	public static String unit_stepUpCoolDownSecs = "seconds";
	public static String unit_lowerThreshold_JVMHeapUsage = "%";
	public static String unit_upperThreshold_JVMHeapUsage= "%";
	public static String unit_lowerThreshold_Memory = "%";
	public static String unit_upperThreshold_Memory= "%";
	public static String unit_lowerThreshold_Throughput = "RPS";
	public static String unit_upperThreshold_Throughput= "RPS";
	public static String unit_lowerThreshold_ResponseTime = "ms";
	public static String unit_upperThreshold_ResponseTime= "ms";
	public static String unit_lowerThreshold_EventLoopLatency = "ms";
	public static String unit_lowerThreshold_HeapUsage = "%";
	public static String unit_upperThreshold_HeapUsage= "%";
	public static String unit_upperThreshold_EventLoopLatency= "ms";
	public static String unit_instanceStepCountDown = "instance(s)"; //this for cmd line tool
	public static String unit_instanceStepCountUp = "instance(s)"; //this for cmd line tool
	public static String TRIGGER_STATWINDOW = "statWindow";
	public static String TRIGGER_BREACHDURATION = "breachDuration";
	public static String TRIGGER_LOWERTHRESHOLD = "lowerThreshold";
	public static String TRIGGER_UPPERTHRESHOLD = "upperThreshold";
	public static String TRIGGER_INSTANCESTEPCOUNTDOWN = "instanceStepCountDown";
	public static String TRIGGER_INSTANCESTEPCOUNTUP = "instanceStepCountUp";
	public static String TRIGGER_STEPDOWNCOOLDOWNSECS = "stepDownCoolDownSecs";
	public static String TRIGGER_STEPUPCOOLDOWN = "stepUpCoolDownSecs";
	public static Map<String, Integer> trigger_default = new HashMap<String, Integer>() //server metric string to metricType
	{
	    {
	        put(TRIGGER_STATWINDOW, value_default_statwindow);
	        put(TRIGGER_BREACHDURATION, value_default_breachDuration);
	        put(TRIGGER_LOWERTHRESHOLD, value_default_lowerThreshold_Memory);
	        put(TRIGGER_UPPERTHRESHOLD, value_default_upperThreshold_Memory);
	        put(TRIGGER_INSTANCESTEPCOUNTDOWN, value_default_instanceStepCountDown);
	        put(TRIGGER_INSTANCESTEPCOUNTUP, value_default_instanceStepCountUp);
	        put(TRIGGER_STEPDOWNCOOLDOWNSECS, value_default_stepDownCoolDownSecs);
	        put(TRIGGER_STEPUPCOOLDOWN, value_default_stepUpCoolDownSecs);
	    }
	};

	/*design of default policy, especially PolicyTrigger part
	 0) field variable are all timely and authoritative value for use, not the default.json
	 1) field variable here is given the value as the default value for range(min/max) or default value here if default.json is not found
	 2) default json can update some range information(max, default) by set the default.json, but for min, as they are always impact from report interval from server, that setting does not work in default.json
	 3) Server will update report interval and so the min value of some field are refreshed, as this refresh happen before the call to server, that range information will refresh before bean validation
	 4) If multi-server does not have the same value of report interval, the last value got take effect, that's bad. that the situation we don't handle now
	 5) local_default return the current default.json updated with latest trigger settings
	 */

    public static Map<String, Map<String, Map<String, String>>> getTriggerRange()
    {
       Map<String, Map<String, Map<String, String>>> range=null;
       range = new HashMap<String, Map<String, Map<String, String>>>();
       Map<String, Map<String, String>> item = null;
       Map<String, String> subitem = null;

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_statwindow));
       subitem.put("message", "{PolicyTrigger.statWindow.Min}");
       item.put("statWindow_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_statwindow));
       subitem.put("message", "{PolicyTrigger.statWindow.Max}");
       item.put("statWindow_Max", subitem);
       range.put("statWindow", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_breachDuration));
       subitem.put("message", "{PolicyTrigger.breachDuration.Min}");
       item.put("breachDuration_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_breachDuration));
       subitem.put("message", "{PolicyTrigger.breachDuration.Max}");
       item.put("breachDuration_Max", subitem);
       range.put("breachDuration", item);


       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_instanceStepCountDown));
       subitem.put("message", "{PolicyTrigger.instanceStepCountDown.Min}");
       item.put("instanceStepCountDown_Min", subitem);

       range.put("instanceStepCountDown", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_instanceStepCountUp));
       subitem.put("message", "{PolicyTrigger.instanceStepCountUp.Min}");
       item.put("instanceStepCountUp_Min", subitem);

       range.put("instanceStepCountUp", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_stepDownCoolDownSecs));
       subitem.put("message", "{PolicyTrigger.stepDownCoolDownSecs.Min}");
       item.put("stepDownCoolDownSecs_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_stepDownCoolDownSecs));
       subitem.put("message", "{PolicyTrigger.stepDownCoolDownSecs.Max}");
       item.put("stepDownCoolDownSecs_Max", subitem);
       range.put("stepDownCoolDownSecs", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_stepUpCoolDownSecs));
       subitem.put("message", "{PolicyTrigger.stepUpCoolDownSecs.Min}");
       item.put("stepUpCoolDownSecs_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_stepUpCoolDownSecs));
       subitem.put("message", "{PolicyTrigger.stepUpCoolDownSecs.Max}");
       item.put("stepUpCoolDownSecs_Max", subitem);
       range.put("stepUpCoolDownSecs", item);

       return range;
    }

    //better design is not transfer this Map as it (its structure) become part of the protocol between user and designer, which is a violation of encapsulation
    // And we can create a getThreshold(String Threshold(lowerThreshold/upperThreshold, String metricType, String key(Min/Max/default)) ) and return just variable
    // like value_max_lowerThreshold_JVMHeapUsage, and getTriggerDefaultThresholdsInt() can just call getThreshold instead
    public static Map<String, Map<String, Map<String, String>>> getThresholdRange()
    {
       Map<String, Map<String, Map<String, String>>> range=null;
       range = new HashMap<String, Map<String, Map<String, String>>>();
       Map<String, Map<String, String>> item = null;
       Map<String, String> subitem = null;

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_lowerThreshold_JVMHeapUsage));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
       item.put("JVMHeapUsage_lowerThreshold_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_JVMHeapUsage));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
       item.put("JVMHeapUsage_lowerThreshold_Max", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_JVMHeapUsage
       subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
       item.put("JVMHeapUsage_upperThreshold_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_upperThreshold_JVMHeapUsage));
       subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
       item.put("JVMHeapUsage_upperThreshold_Max", subitem);
       range.put("JVMHeapUsage", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_lowerThreshold_Memory));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
       item.put("Memory_lowerThreshold_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_Memory));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
       item.put("Memory_lowerThreshold_Max", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_Memory
       subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
       item.put("Memory_upperThreshold_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_upperThreshold_Memory));
       subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
       item.put("Memory_upperThreshold_Max", subitem);
       range.put("Memory", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_lowerThreshold_Throughput));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
       item.put("Throughput_lowerThreshold_Min", subitem);
       /* No max value
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_Throughput));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
       item.put("Throughput_lowerThreshold_Max", subitem);
       */
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_Throughput
       subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
       item.put("Throughput_upperThreshold_Min", subitem);
       /* No max value
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_Throughput));
       subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
       item.put("Throughput_upperThreshold_Max", subitem);
       */
       range.put("Throughput", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_lowerThreshold_ResponseTime));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
       item.put("ResponseTime_lowerThreshold_Min", subitem);
       /* No max value
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_ResponseTime));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
       item.put("ResponseTime_lowerThreshold_Max", subitem);
       */
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(1)); // hard coded instead of value_max_upperThreshold_ResponseTime
       subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
       item.put("ResponseTime_upperThreshold_Min", subitem);
       /* No max value
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_upperThreshold_ResponseTime));
       subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
       item.put("ResponseTime_upperThreshold_Max", subitem);
       */
       range.put("ResponseTime", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_lowerThreshold_EventLoopLatency));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
       item.put("EventLoopLatency_lowerThreshold_Min", subitem);
       /* No max value
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_EventLoopLatency));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
       item.put("EventLoopLatency_lowerThreshold_Max", subitem);
       */
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(1)); // hard coded instead of value_max_upperThreshold_EventLoopLatency
       subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
       item.put("EventLoopLatency_upperThreshold_Min", subitem);
       /* No max value
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_upperThreshold_EventLoopLatency));
       subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
       item.put("EventLoopLatency_upperThreshold_Max", subitem);
       */
       range.put("EventLoopLatency", item);

       item = new HashMap<String, Map<String, String>>();
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_min_lowerThreshold_HeapUsage));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
       item.put("HeapUsage_lowerThreshold_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_lowerThreshold_HeapUsage));
       subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
       item.put("HeapUsage_lowerThreshold_Max", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_HeapUsage
       subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
       item.put("HeapUsage_upperThreshold_Min", subitem);
       subitem = new HashMap<String, String>();
       subitem.put("value", String.valueOf(value_max_upperThreshold_HeapUsage));
       subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
       item.put("HeapUsage_upperThreshold_Max", subitem);
       range.put("HeapUsage", item);

       return range;
    }

    public static Map<String, Map<String, Map<String, Map<String, String>>>> getTriggerRangeByTriggerType()
    {
    	Map<String, Map<String, Map<String, Map<String, String>>>> mulit_range=null;
    	mulit_range = new HashMap<String, Map<String, Map<String, Map<String, String>>>>();

        Map<String, Map<String, Map<String, String>>> range = getTriggerRange(); //get common part of range
        Map<String, Map<String, String>> item = null;
        Map<String, String> subitem = null;

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_min_lowerThreshold_JVMHeapUsage));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
        item.put("lowerThreshold_Min", subitem);
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_max_lowerThreshold_JVMHeapUsage));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
        item.put("lowerThreshold_Max", subitem);
        range.put("lowerThreshold", item);

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_JVMHeapUsage
        subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
        item.put("upperThreshold_Min", subitem);
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_max_upperThreshold_JVMHeapUsage));
        subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
        item.put("upperThreshold_Max", subitem);
        range.put("upperThreshold", item);

        mulit_range.put("trigger_JVMHeapUsage", range);

        range = getTriggerRange();

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_min_lowerThreshold_Memory));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
        item.put("lowerThreshold_Min", subitem);
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_max_lowerThreshold_Memory));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
        item.put("lowerThreshold_Max", subitem);
        range.put("lowerThreshold", item);

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(1));
        subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
        item.put("upperThreshold_Min", subitem);
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_max_upperThreshold_Memory));
        subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
        item.put("upperThreshold_Max", subitem);
        range.put("upperThreshold", item);

        mulit_range.put("trigger_Memory", range);

        range = getTriggerRange();

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_min_lowerThreshold_Throughput));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
        item.put("lowerThreshold_Min", subitem);
        range.put("lowerThreshold", item);

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_Throughput
        subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
        item.put("upperThreshold_Min", subitem);
        range.put("upperThreshold", item);

        mulit_range.put("trigger_Throughput", range);

        range = getTriggerRange();

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_min_lowerThreshold_ResponseTime));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
        item.put("lowerThreshold_Min", subitem);
        range.put("lowerThreshold", item);

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(1)); // hard coded instead of value_max_upperThreshold_ResponseTime
        subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
        item.put("upperThreshold_Min", subitem);
        range.put("upperThreshold", item);

        mulit_range.put("trigger_ResponseTime", range);

        range = getTriggerRange();

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_min_lowerThreshold_EventLoopLatency));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
        item.put("lowerThreshold_Min", subitem);
        range.put("lowerThreshold", item);

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(1)); // hard coded instead of value_max_upperThreshold_EventLoopLatency
        subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
        item.put("upperThreshold_Min", subitem);
        range.put("upperThreshold", item);

        mulit_range.put("trigger_EventLoopLatency", range);

        range = getTriggerRange();

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_min_lowerThreshold_HeapUsage));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Min}");
        item.put("lowerThreshold_Min", subitem);
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_max_lowerThreshold_HeapUsage));
        subitem.put("message", "{PolicyTrigger.lowerThreshold.Max}");
        item.put("lowerThreshold_Max", subitem);
        range.put("lowerThreshold", item);

        item = new HashMap<String, Map<String, String>>();
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(1)); // hard coded instead of value_min_upperThreshold_HeapUsage
        subitem.put("message", "{PolicyTrigger.upperThreshold.Min}");
        item.put("upperThreshold_Min", subitem);
        subitem = new HashMap<String, String>();
        subitem.put("value", String.valueOf(value_max_upperThreshold_HeapUsage));
        subitem.put("message", "{PolicyTrigger.upperThreshold.Max}");
        item.put("upperThreshold_Max", subitem);
        range.put("upperThreshold", item);

        mulit_range.put("trigger_HeapUsage", range);

        return mulit_range;
    }



    public static void updateTriggerRange(int interval) //runtime update trigger value
    {
    	REPORTINTERVAL=interval;
    	value_metric_refresh_interval = REPORTINTERVAL * 1000;
    	value_min_statwindow = REPORTINTERVAL;
    	value_min_breachDuration = REPORTINTERVAL;
    	//value_min_cooldown = REPORTINTERVAL;
    	value_min_stepUpCoolDownSecs = REPORTINTERVAL;
    	value_min_stepDownCoolDownSecs = REPORTINTERVAL;
    	metricTimeRange = REPORTINTERVAL * 60 /60;
    }

    public static String updateDefaultJSON(String json_file) throws JsonParseException, JsonMappingException, IOException
    {
     	JsonNode top = mapper.readTree(json_file);
        Iterator<Entry<String, JsonNode>> policy_items= top.fields();
		while (policy_items.hasNext()){
			Entry<String, JsonNode> policy_item = policy_items.next();
			String item_name = policy_item.getKey();
			logger.info("item_name: " + item_name);
			JsonNode item_value = policy_item.getValue();
			if(item_name.equals("policyTriggers")) {
				for(JsonNode policyTrigger : item_value) { //only one
		           /* "statWindow": "300,120,3600",
		            "breachDuration": "600,120,3600",
		            "lowerThreshold": "30,1,100",
		            "upperThreshold": "80,x,100",
		            "instanceStepCountDown": "1,1,x",
		            "instanceStepCountUp": "1,1,x",
		            "stepDownCoolDownSecs": "600,120,3600",
		            "stepUpCoolDownSecs": "600,120,3600" */
					((ObjectNode)policyTrigger).put("statWindow", value_default_statwindow + "," + value_min_statwindow + "," + value_max_statwindow+ "," + unit_statwindow);
					((ObjectNode)policyTrigger).put("breachDuration", value_default_breachDuration + "," + value_min_breachDuration + "," + value_max_breachDuration + "," + unit_breachDuration);
					//((ObjectNode)policyTrigger).put("lowerThreshold", value_default_lowerThreshold + "," + value_min_lowerThreshold + "," + value_max_lowerThreshold);
					//((ObjectNode)policyTrigger).put("upperThreshold", value_default_upperThreshold + "," + value_min_upperThreshold + "," + value_max_upperThreshold);
					((ObjectNode)policyTrigger).put("instanceStepCountDown", value_default_instanceStepCountDown + "," + value_min_instanceStepCountDown + "," + value_max_instanceStepCountDown + "," + unit_instanceStepCountDown);
					((ObjectNode)policyTrigger).put("instanceStepCountUp", value_default_instanceStepCountUp + "," + value_min_instanceStepCountUp + "," + value_max_instanceStepCountUp + "," + unit_instanceStepCountUp);
					((ObjectNode)policyTrigger).put("stepDownCoolDownSecs", value_default_stepDownCoolDownSecs + "," + value_min_stepDownCoolDownSecs + "," + value_max_stepDownCoolDownSecs + "," + unit_stepDownCoolDownSecs);
					((ObjectNode)policyTrigger).put("stepUpCoolDownSecs", value_default_stepUpCoolDownSecs + "," + value_min_stepUpCoolDownSecs + "," + value_max_stepUpCoolDownSecs + "," + unit_stepUpCoolDownSecs);
					ObjectNode ThresholdjNode = mapper.createObjectNode();
					ObjectNode jNode = mapper.createObjectNode();
					jNode.put("lowerThreshold", value_default_lowerThreshold_JVMHeapUsage + "," + value_min_lowerThreshold_JVMHeapUsage + "," + value_max_lowerThreshold_JVMHeapUsage + "," + unit_lowerThreshold_JVMHeapUsage);
					jNode.put("upperThreshold", value_default_upperThreshold_JVMHeapUsage + "," + value_min_upperThreshold_JVMHeapUsage + "," + value_max_upperThreshold_JVMHeapUsage + "," + unit_upperThreshold_JVMHeapUsage);
					ThresholdjNode.put("JVMHeapUsage", jNode);
					jNode = mapper.createObjectNode();
					jNode.put("lowerThreshold", value_default_lowerThreshold_Memory + "," + value_min_lowerThreshold_Memory + "," + value_max_lowerThreshold_Memory + "," + unit_lowerThreshold_Memory);
					jNode.put("upperThreshold", value_default_upperThreshold_Memory + "," + value_min_upperThreshold_Memory + "," + value_max_upperThreshold_Memory + "," + unit_upperThreshold_Memory);
					ThresholdjNode.put("Memory", jNode);
					jNode = mapper.createObjectNode();
					jNode.put("lowerThreshold", value_default_lowerThreshold_Throughput + "," + value_min_lowerThreshold_Throughput + "," + value_max_lowerThreshold_Throughput + "," + unit_lowerThreshold_Throughput);
					jNode.put("upperThreshold", value_default_upperThreshold_Throughput + "," + value_min_upperThreshold_Throughput + "," + value_max_upperThreshold_Throughput + "," + unit_upperThreshold_Throughput);
					ThresholdjNode.put("Throughput", jNode);
					jNode = mapper.createObjectNode();
					jNode.put("lowerThreshold", value_default_lowerThreshold_ResponseTime + "," + value_min_lowerThreshold_ResponseTime + "," + value_max_lowerThreshold_ResponseTime + "," + unit_lowerThreshold_ResponseTime);
					jNode.put("upperThreshold", value_default_upperThreshold_ResponseTime + "," + value_min_upperThreshold_ResponseTime + "," + value_max_upperThreshold_ResponseTime + "," + unit_upperThreshold_ResponseTime);
					ThresholdjNode.put("ResponseTime", jNode);
					jNode = mapper.createObjectNode();
					jNode.put("lowerThreshold", value_default_lowerThreshold_EventLoopLatency + "," + value_min_lowerThreshold_EventLoopLatency + "," + value_max_lowerThreshold_EventLoopLatency + "," + unit_lowerThreshold_EventLoopLatency);
					jNode.put("upperThreshold", value_default_upperThreshold_EventLoopLatency + "," + value_min_upperThreshold_EventLoopLatency + "," + value_max_upperThreshold_EventLoopLatency + "," + unit_upperThreshold_EventLoopLatency);
					ThresholdjNode.put("EventLoopLatency", jNode);
					jNode = mapper.createObjectNode();
					jNode.put("lowerThreshold", value_default_lowerThreshold_HeapUsage + "," + value_min_lowerThreshold_HeapUsage + "," + value_max_lowerThreshold_HeapUsage + "," + unit_lowerThreshold_HeapUsage);
					jNode.put("upperThreshold", value_default_upperThreshold_HeapUsage + "," + value_min_upperThreshold_HeapUsage + "," + value_max_upperThreshold_HeapUsage + "," + unit_upperThreshold_HeapUsage);
					ThresholdjNode.put("HeapUsage", jNode);
					((ObjectNode)policyTrigger).put("thresholds", ThresholdjNode);
				}
			}
			else if(item_name.equals("AppType-Metric-Mapping")) {
				for (String appType : appType_metric_mapping.keySet()) {
					String [] metric_list = appType_metric_mapping.get(appType);
					if (!(appType.equals("default"))){
					    //appType = appType.substring(0, 1).toUpperCase() + appType.substring(1);
						appType = appType_Display_mapping.get(appType);
					}
				    JsonNodeFactory factory = JsonNodeFactory.instance;
				    ArrayNode aaData = new ArrayNode(factory);
				    for (String metric : metric_list) {
				    	aaData.add(metric);
				    }
				    ((ObjectNode)item_value).put(appType, aaData);
				}
			}
		}
    	return top.toString();
    }

    public static String [] getMetricTypeByAppType(String appType) { //throws JsonProcessingException {
    	logger.debug("getMetricTypeByAppType for appType: " + appType);
    	//logger.info("current appType_metric_mapping :" + mapper.writeValueAsString(appType_metric_mapping));
    	if (Arrays.asList(APPTYPE).contains(appType)) {
    		String [] specified_metric = (String [])appType_metric_mapping.get(appType);
    		if (specified_metric != null){
    			logger.debug("found metric array for appType: " + appType);
    			return specified_metric;
    		}
    		else
    			return appType_metric_mapping.get(APP_TYPE_DEFAULT);
    	}
    	return null;
    }

	public static int getTriggerDefaultInt(String key) {
		return trigger_default.get(key).intValue();
	}

}