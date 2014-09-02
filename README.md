hybris-discobit-agent
====================


[y] Hybris discoBit remote configuration
-----------------------------------------------------------------
This is a fork of discoBits' REST client implementation. The additional Java Agent will connect to a discoBit repository, fetch remote properties via configuration UUID and finally inject those into the system context when Hybris is bootstrapped.

See the wiki for details on setup.



Original README.md:

discoBit - Remote Application Configuration Management
------------------------------------------------------------------
*discoBit is a service which enables remote configuration of applications.
Configurations are read from the central server during bootstrap or on demand by your software.*

#About
Instead of providing each installed application, script or process with local configuration you shift everything to a central instance which serves as a single point of truth. All properties are organized in configuration buckets which are uniquely identified and secured from unauthorized access.

Your software calls the central service cluster via REST and reads the applied properties when appropriate. You need to provide a single remote-configuration-ID or a unique identifier for the calling node (hostname, IP) and security credentials to access the remote configuration.

Apply changes to your configs for massive infrastructures without any need for rollouts or restarts.

See www.discobit.com

# Java Client Library - Usage
This library eases access to the REST based API for discoBit. 
