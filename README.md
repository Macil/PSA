PSA
===

The PSA (Public Service Announcement) plugin for Bukkit regularly
outputs messages to the server chat. Messages are set in the
config.yml file, and they can be weighted so some messages appear more
often than others.

Optionally, if PSA is running on a server with the Spout plugin, PSA
supports having specific messages contain a recommendation for players
to download the Spoutcraft client in order to use all of the server's
features. Players already using the Spoutcraft client won't see the
recommendation.

Installing
----------

Place the Restarter.jar file in the plugins/ directory. After the
first run, a Restarter/config.yml file will be generated with default
values.

Configuration
-------------

The time value is how long in minutes to wait between displaying
messages.

The variance value lets you set a maximum amount for the time between
messages to vary. PSA will wait for an amount of time in the range
[minutesToRestart-variance, minutesToRestart+variance] before
displaying a new message. For example, if time is 12 and variance is
4, then PSA will wait for a random amount of time between 8 and 16
minutes before displaying a message. If you set variance to 0, then
the messages will always be exactly _time_ minutes apart.

The color value is the color for the messages to appear in. It can be
any color on the list at
http://jd.bukkit.org/apidocs/org/bukkit/ChatColor.html

The messages are all defined under the messages section. The first
heading of the message is the message's name. (The message names in
the default config.yml are "help", "rules", and "spout" for example.)

The message's content goes after the "message:" part. If the symbols
"|-" immediately come after "message:", then the message will span the
next few lines, but it must be indented! Look up the YAML format for
more information.

The "chance:" part defines the message's probability weight. The
default chance value is 100 if unspecified. A message with the chance
of 50 is half as likely to be displayed at any time as a message with
the chance of 100. The chance is a floating point value, so
non-integer values (like 0.5) are okay.

The "spout:" part defines whether the message should be followed by a
recommendation to use the Spoutcraft client for players not already
using it. This option defaults to false if unspecified for a message,
and has no effect if the server is not using the Spout plugin.

Commands
--------

/announce <some message>

Immediately announces the given message in the style of PSA's messages
in chat. Requires the "psa.announce" permission.

/psanow <message name>

Causes the named message that was defined in config.yml to be
displayed in the server chat. Requires the "psa.now" permission.

/psatest <message name>

Causes the named message that was defined in config.yml to be
displayed back to the caller only. This is useful for testing that the
message fits on the screen. Requires the "psa.test" permission.

/psareload

Causes PSA to reload its config.yml file. This will reload all
messages and settings, and reset the time until the next message is
displayed automatically in chat. Requires the "psa.reload" permission.
