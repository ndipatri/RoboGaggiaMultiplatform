# DefaultApi

All URIs are relative to *https://api.particle.io*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**callFunction**](DefaultApi.md#callFunction) | **POST** /v1/devices/{deviceId}/{functionName} | Call a function |
| [**getDevice**](DefaultApi.md#getDevice) | **GET** /v1/devices/{deviceId} | Get device information |
| [**getDevices**](DefaultApi.md#getDevices) | **GET** /v1/devices | List devices |
| [**getVariableValue**](DefaultApi.md#getVariableValue) | **GET** /v1/devices/{deviceId}/{varName} | Get a variable value |
| [**oauthTokenPost**](DefaultApi.md#oauthTokenPost) | **POST** /oauth/token | Generate a customer scoped access token |
| [**userGet**](DefaultApi.md#userGet) | **GET** /user | Get user |
| [**userPasswordResetPost**](DefaultApi.md#userPasswordResetPost) | **POST** /user/password-reset | Forgot password |
| [**userPut**](DefaultApi.md#userPut) | **PUT** /user | Delete user |
| [**v1AccessTokensCurrentDelete**](DefaultApi.md#v1AccessTokensCurrentDelete) | **DELETE** /v1/access_tokens/current | Delete current access token |
| [**v1AccessTokensCurrentGet**](DefaultApi.md#v1AccessTokensCurrentGet) | **GET** /v1/access_tokens/current | Get the current access token information |
| [**v1AccessTokensDelete**](DefaultApi.md#v1AccessTokensDelete) | **DELETE** /v1/access_tokens | Delete all active access tokens |
| [**v1AccessTokensGet**](DefaultApi.md#v1AccessTokensGet) | **GET** /v1/access_tokens | List access tokens |
| [**v1AccessTokensTokenDelete**](DefaultApi.md#v1AccessTokensTokenDelete) | **DELETE** /v1/access_tokens/{token} | Delete an access token |
| [**v1BinariesPost**](DefaultApi.md#v1BinariesPost) | **POST** /v1/binaries | Compile source code |
| [**v1BuildTargetsGet**](DefaultApi.md#v1BuildTargetsGet) | **GET** /v1/build_targets | List firmware build targets |
| [**v1ClientsClientIdDelete**](DefaultApi.md#v1ClientsClientIdDelete) | **DELETE** /v1/clients/{clientId} | Delete a client |
| [**v1ClientsClientIdPut**](DefaultApi.md#v1ClientsClientIdPut) | **PUT** /v1/clients/{clientId} | Update a client |
| [**v1ClientsGet**](DefaultApi.md#v1ClientsGet) | **GET** /v1/clients | List clients |
| [**v1ClientsPost**](DefaultApi.md#v1ClientsPost) | **POST** /v1/clients | Create a client |
| [**v1DevicesDeviceIdDelete**](DefaultApi.md#v1DevicesDeviceIdDelete) | **DELETE** /v1/devices/{deviceId} | Unclaim device |
| [**v1DevicesDeviceIdEventsEventPrefixGet**](DefaultApi.md#v1DevicesDeviceIdEventsEventPrefixGet) | **GET** /v1/devices/{deviceId}/events/{eventPrefix} | Get a stream of events for a device |
| [**v1DevicesDeviceIdPingPut**](DefaultApi.md#v1DevicesDeviceIdPingPut) | **PUT** /v1/devices/{deviceId}/ping | Ping a device |
| [**v1DevicesDeviceIdPut**](DefaultApi.md#v1DevicesDeviceIdPut) | **PUT** /v1/devices/{deviceId} | Flash a device with a bundle |
| [**v1DevicesEventsEventPrefixGet**](DefaultApi.md#v1DevicesEventsEventPrefixGet) | **GET** /v1/devices/events/{eventPrefix} | Get a stream of your events |
| [**v1DevicesEventsPost**](DefaultApi.md#v1DevicesEventsPost) | **POST** /v1/devices/events | Publish an event |
| [**v1DevicesPost**](DefaultApi.md#v1DevicesPost) | **POST** /v1/devices | Claim a device |
| [**v1DiagnosticsDeviceIdGet**](DefaultApi.md#v1DiagnosticsDeviceIdGet) | **GET** /v1/diagnostics/{deviceId} | Get all historical device vitals |
| [**v1DiagnosticsDeviceIdLastGet**](DefaultApi.md#v1DiagnosticsDeviceIdLastGet) | **GET** /v1/diagnostics/{deviceId}/last | Get last known device vitals |
| [**v1DiagnosticsDeviceIdUpdatePost**](DefaultApi.md#v1DiagnosticsDeviceIdUpdatePost) | **POST** /v1/diagnostics/{deviceId}/update | Refresh device vitals |
| [**v1EventsEventPrefixGet**](DefaultApi.md#v1EventsEventPrefixGet) | **GET** /v1/events/{eventPrefix} | Get a stream of events |
| [**v1IntegrationsGet**](DefaultApi.md#v1IntegrationsGet) | **GET** /v1/integrations | List integrations |
| [**v1IntegrationsIntegrationIdDelete**](DefaultApi.md#v1IntegrationsIntegrationIdDelete) | **DELETE** /v1/integrations/{integrationId} | Delete an integration |
| [**v1IntegrationsIntegrationIdGet**](DefaultApi.md#v1IntegrationsIntegrationIdGet) | **GET** /v1/integrations/{integrationId} | Get integration |
| [**v1IntegrationsIntegrationIdPut**](DefaultApi.md#v1IntegrationsIntegrationIdPut) | **PUT** /v1/integrations/{integrationId} | Edit Google Maps Integration |
| [**v1IntegrationsIntegrationIdTestPost**](DefaultApi.md#v1IntegrationsIntegrationIdTestPost) | **POST** /v1/integrations/{integrationId}/test | Test an integration |
| [**v1IntegrationsPost**](DefaultApi.md#v1IntegrationsPost) | **POST** /v1/integrations | Enable Google Maps integration |
| [**v1OrgsGet**](DefaultApi.md#v1OrgsGet) | **GET** /v1/orgs | List organizations |
| [**v1OrgsOrgIdOrSlugGet**](DefaultApi.md#v1OrgsOrgIdOrSlugGet) | **GET** /v1/orgs/{orgIdOrSlug} | Retrieve an organization |
| [**v1OrgsOrgIdOrSlugProductsGet**](DefaultApi.md#v1OrgsOrgIdOrSlugProductsGet) | **GET** /v1/orgs/{orgIdOrSlug}/products | List organization products |
| [**v1OrgsOrgIdOrSlugServiceAgreementsGet**](DefaultApi.md#v1OrgsOrgIdOrSlugServiceAgreementsGet) | **GET** /v1/orgs/{orgIdOrSlug}/service_agreements | Get organization service agreements |
| [**v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost**](DefaultApi.md#v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost) | **POST** /v1/orgs/{orgSlugOrId}/service_agreements/{serviceAgreementId}/usage_reports | Create an org usage report |
| [**v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet**](DefaultApi.md#v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet) | **GET** /v1/orgs/{orgSlugOrId}/usage_reports/{usageReportId} | Get an org usage report |
| [**v1ProductsProductIdOrSlugConfigDelete**](DefaultApi.md#v1ProductsProductIdOrSlugConfigDelete) | **DELETE** /v1/products/{productIdOrSlug}/config | Delete product configuration schema |
| [**v1ProductsProductIdOrSlugConfigDeviceIdDelete**](DefaultApi.md#v1ProductsProductIdOrSlugConfigDeviceIdDelete) | **DELETE** /v1/products/{productIdOrSlug}/config/{deviceId} | Delete device configuration schema |
| [**v1ProductsProductIdOrSlugConfigDeviceIdGet**](DefaultApi.md#v1ProductsProductIdOrSlugConfigDeviceIdGet) | **GET** /v1/products/{productIdOrSlug}/config/{deviceId} | Get device schema |
| [**v1ProductsProductIdOrSlugConfigDeviceIdPut**](DefaultApi.md#v1ProductsProductIdOrSlugConfigDeviceIdPut) | **PUT** /v1/products/{productIdOrSlug}/config/{deviceId} | Set device configuration schema |
| [**v1ProductsProductIdOrSlugConfigGet**](DefaultApi.md#v1ProductsProductIdOrSlugConfigGet) | **GET** /v1/products/{productIdOrSlug}/config | Get product schema |
| [**v1ProductsProductIdOrSlugConfigPut**](DefaultApi.md#v1ProductsProductIdOrSlugConfigPut) | **PUT** /v1/products/{productIdOrSlug}/config | Set product configuration schema |
| [**v1ProductsProductIdOrSlugCustomersCustomerEmailDelete**](DefaultApi.md#v1ProductsProductIdOrSlugCustomersCustomerEmailDelete) | **DELETE** /v1/products/{productIdOrSlug}/customers/{customerEmail} | Delete a customer |
| [**v1ProductsProductIdOrSlugCustomersCustomerEmailPut**](DefaultApi.md#v1ProductsProductIdOrSlugCustomersCustomerEmailPut) | **PUT** /v1/products/{productIdOrSlug}/customers/{customerEmail} | Update customer password |
| [**v1ProductsProductIdOrSlugCustomersGet**](DefaultApi.md#v1ProductsProductIdOrSlugCustomersGet) | **GET** /v1/products/{productIdOrSlug}/customers | List customers for a product |
| [**v1ProductsProductIdOrSlugCustomersPost**](DefaultApi.md#v1ProductsProductIdOrSlugCustomersPost) | **POST** /v1/products/{productIdOrSlug}/customers | Create a customer - Implicit |
| [**v1ProductsProductIdOrSlugDevicesDeviceIdDelete**](DefaultApi.md#v1ProductsProductIdOrSlugDevicesDeviceIdDelete) | **DELETE** /v1/products/{productIdOrSlug}/devices/{deviceId} | Deny a quarantined device |
| [**v1ProductsProductIdOrSlugDevicesDeviceIdGet**](DefaultApi.md#v1ProductsProductIdOrSlugDevicesDeviceIdGet) | **GET** /v1/products/{productIdOrSlug}/devices/{deviceId} | Get product device information |
| [**v1ProductsProductIdOrSlugDevicesDeviceIdPut**](DefaultApi.md#v1ProductsProductIdOrSlugDevicesDeviceIdPut) | **PUT** /v1/products/{productIdOrSlug}/devices/{deviceId} | Assign groups to a device |
| [**v1ProductsProductIdOrSlugDevicesGet**](DefaultApi.md#v1ProductsProductIdOrSlugDevicesGet) | **GET** /v1/products/{productIdOrSlug}/devices | List devices in a product |
| [**v1ProductsProductIdOrSlugDevicesPost**](DefaultApi.md#v1ProductsProductIdOrSlugDevicesPost) | **POST** /v1/products/{productIdOrSlug}/devices | Approve a quarantined device |
| [**v1ProductsProductIdOrSlugDevicesPut**](DefaultApi.md#v1ProductsProductIdOrSlugDevicesPut) | **PUT** /v1/products/{productIdOrSlug}/devices | Batch assign groups to devices |
| [**v1ProductsProductIdOrSlugEventsEventPrefixGet**](DefaultApi.md#v1ProductsProductIdOrSlugEventsEventPrefixGet) | **GET** /v1/products/{productIdOrSlug}/events/{eventPrefix} | Product event stream |
| [**v1ProductsProductIdOrSlugEventsPost**](DefaultApi.md#v1ProductsProductIdOrSlugEventsPost) | **POST** /v1/products/{productIdOrSlug}/events | Publish a product event |
| [**v1ProductsProductIdOrSlugFirmwareGet**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwareGet) | **GET** /v1/products/{productIdOrSlug}/firmware | List all product firmwares |
| [**v1ProductsProductIdOrSlugFirmwarePost**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwarePost) | **POST** /v1/products/{productIdOrSlug}/firmware | Upload product firmware |
| [**v1ProductsProductIdOrSlugFirmwareReleasePut**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwareReleasePut) | **PUT** /v1/products/{productIdOrSlug}/firmware/release | Release product firmware |
| [**v1ProductsProductIdOrSlugFirmwareVersionBinaryGet**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwareVersionBinaryGet) | **GET** /v1/products/{productIdOrSlug}/firmware/{version}/binary | Download firmware binary |
| [**v1ProductsProductIdOrSlugFirmwareVersionDelete**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwareVersionDelete) | **DELETE** /v1/products/{productIdOrSlug}/firmware/{version} | Delete unreleased firmware binary |
| [**v1ProductsProductIdOrSlugFirmwareVersionGet**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwareVersionGet) | **GET** /v1/products/{productIdOrSlug}/firmware/{version} | Get product firmware |
| [**v1ProductsProductIdOrSlugFirmwareVersionPut**](DefaultApi.md#v1ProductsProductIdOrSlugFirmwareVersionPut) | **PUT** /v1/products/{productIdOrSlug}/firmware/{version} | Edit product firmware |
| [**v1ProductsProductIdOrSlugFleetLocationsGet**](DefaultApi.md#v1ProductsProductIdOrSlugFleetLocationsGet) | **GET** /v1/products/{productIdOrSlug}/fleet_locations | Query fleet-wide locations for devices within a product |
| [**v1ProductsProductIdOrSlugGet**](DefaultApi.md#v1ProductsProductIdOrSlugGet) | **GET** /v1/products/{productIdOrSlug} | Retrieve a product |
| [**v1ProductsProductIdOrSlugGroupsGet**](DefaultApi.md#v1ProductsProductIdOrSlugGroupsGet) | **GET** /v1/products/{productIdOrSlug}/groups | List device groups |
| [**v1ProductsProductIdOrSlugGroupsGroupNameDelete**](DefaultApi.md#v1ProductsProductIdOrSlugGroupsGroupNameDelete) | **DELETE** /v1/products/{productIdOrSlug}/groups/{groupName} | Delete device group |
| [**v1ProductsProductIdOrSlugGroupsGroupNameGet**](DefaultApi.md#v1ProductsProductIdOrSlugGroupsGroupNameGet) | **GET** /v1/products/{productIdOrSlug}/groups/{groupName} | Get device group |
| [**v1ProductsProductIdOrSlugGroupsGroupNamePut**](DefaultApi.md#v1ProductsProductIdOrSlugGroupsGroupNamePut) | **PUT** /v1/products/{productIdOrSlug}/groups/{groupName} | Edit device group |
| [**v1ProductsProductIdOrSlugGroupsPost**](DefaultApi.md#v1ProductsProductIdOrSlugGroupsPost) | **POST** /v1/products/{productIdOrSlug}/groups | Create device group |
| [**v1ProductsProductIdOrSlugImpactGet**](DefaultApi.md#v1ProductsProductIdOrSlugImpactGet) | **GET** /v1/products/{productIdOrSlug}/impact | Impact of taking action |
| [**v1ProductsProductIdOrSlugLocationsDeviceIdGet**](DefaultApi.md#v1ProductsProductIdOrSlugLocationsDeviceIdGet) | **GET** /v1/products/{productIdOrSlug}/locations/{deviceId} | Query location for one device within a product |
| [**v1ProductsProductIdOrSlugLocationsGet**](DefaultApi.md#v1ProductsProductIdOrSlugLocationsGet) | **GET** /v1/products/{productIdOrSlug}/locations | Query location for devices within a product |
| [**v1ProductsProductIdOrSlugMetricsEventsGet**](DefaultApi.md#v1ProductsProductIdOrSlugMetricsEventsGet) | **GET** /v1/products/{productIdOrSlug}/metrics/events | Get event traffic health metrics |
| [**v1ProductsProductIdOrSlugMetricsFunctionsGet**](DefaultApi.md#v1ProductsProductIdOrSlugMetricsFunctionsGet) | **GET** /v1/products/{productIdOrSlug}/metrics/functions | Get cloud function call health metrics |
| [**v1ProductsProductIdOrSlugMetricsIntegrationGet**](DefaultApi.md#v1ProductsProductIdOrSlugMetricsIntegrationGet) | **GET** /v1/products/{productIdOrSlug}/metrics/integration | Get integration traffic health metrics |
| [**v1ProductsProductIdOrSlugMetricsOnlineGet**](DefaultApi.md#v1ProductsProductIdOrSlugMetricsOnlineGet) | **GET** /v1/products/{productIdOrSlug}/metrics/online | Get online devices metrics |
| [**v1ProductsProductIdOrSlugMetricsVariablesGet**](DefaultApi.md#v1ProductsProductIdOrSlugMetricsVariablesGet) | **GET** /v1/products/{productIdOrSlug}/metrics/variables | Get cloud variable request health metrics |
| [**v1ProductsProductIdOrSlugSimsDataUsageGet**](DefaultApi.md#v1ProductsProductIdOrSlugSimsDataUsageGet) | **GET** /v1/products/{productIdOrSlug}/sims/data_usage | Get data usage for product fleet |
| [**v1ProductsProductIdOrSlugSimsPost**](DefaultApi.md#v1ProductsProductIdOrSlugSimsPost) | **POST** /v1/products/{productIdOrSlug}/sims | Import and activate product SIMs |
| [**v1ProductsProductIdOrSlugTeamGet**](DefaultApi.md#v1ProductsProductIdOrSlugTeamGet) | **GET** /v1/products/{productIdOrSlug}/team | List team members |
| [**v1ProductsProductIdOrSlugTeamPost**](DefaultApi.md#v1ProductsProductIdOrSlugTeamPost) | **POST** /v1/products/{productIdOrSlug}/team | Create an API user |
| [**v1ProductsProductIdOrSlugTeamUsernameDelete**](DefaultApi.md#v1ProductsProductIdOrSlugTeamUsernameDelete) | **DELETE** /v1/products/{productIdOrSlug}/team/{username} | Remove team member |
| [**v1ProductsProductIdOrSlugTeamUsernamePost**](DefaultApi.md#v1ProductsProductIdOrSlugTeamUsernamePost) | **POST** /v1/products/{productIdOrSlug}/team/{username} | Update team member |
| [**v1ProductsProductIdOrSlugTeamUsernameTokenPut**](DefaultApi.md#v1ProductsProductIdOrSlugTeamUsernameTokenPut) | **PUT** /v1/products/{productIdOrSlug}/team/{username}/token | Regenerate programmatic user&#39;s token |
| [**v1SerialNumbersSerialNumberGet**](DefaultApi.md#v1SerialNumbersSerialNumberGet) | **GET** /v1/serial_numbers/{serial_number} | Look up device identification from a serial number |
| [**v1SimsGet**](DefaultApi.md#v1SimsGet) | **GET** /v1/sims | List SIM cards |
| [**v1SimsIccidDataUsageGet**](DefaultApi.md#v1SimsIccidDataUsageGet) | **GET** /v1/sims/{iccid}/data_usage | Get data usage |
| [**v1SimsIccidDelete**](DefaultApi.md#v1SimsIccidDelete) | **DELETE** /v1/sims/{iccid} | Release SIM from account |
| [**v1SimsIccidGet**](DefaultApi.md#v1SimsIccidGet) | **GET** /v1/sims/{iccid} | Get SIM information |
| [**v1SimsIccidPut**](DefaultApi.md#v1SimsIccidPut) | **PUT** /v1/sims/{iccid} | Reactivate SIM |
| [**v1SimsIccidStatusGet**](DefaultApi.md#v1SimsIccidStatusGet) | **GET** /v1/sims/{iccid}/status | Get cellular network status |
| [**v1UserProductsGet**](DefaultApi.md#v1UserProductsGet) | **GET** /v1/user/products | List products |
| [**v1UserServiceAgreementsGet**](DefaultApi.md#v1UserServiceAgreementsGet) | **GET** /v1/user/service_agreements | Get user service agreements |
| [**v1UserServiceAgreementsServiceAgreementIdNotificationsGet**](DefaultApi.md#v1UserServiceAgreementsServiceAgreementIdNotificationsGet) | **GET** /v1/user/service_agreements/{serviceAgreementId}/notifications | Get notifications for current usage period |
| [**v1UserServiceAgreementsServiceAgreementIdUsageReportsPost**](DefaultApi.md#v1UserServiceAgreementsServiceAgreementIdUsageReportsPost) | **POST** /v1/user/service_agreements/{serviceAgreementId}/usage_reports | Create a user usage report |
| [**v1UserUsageReportsUsageReportIdGet**](DefaultApi.md#v1UserUsageReportsUsageReportIdGet) | **GET** /v1/user/usage_reports/{usageReportId} | Get a user usage report |
| [**v1UsersLedgersGet**](DefaultApi.md#v1UsersLedgersGet) | **GET** /v1/users/ledgers | List ledger definitions |
| [**v1UsersLedgersLedgerNameDelete**](DefaultApi.md#v1UsersLedgersLedgerNameDelete) | **DELETE** /v1/users/ledgers/{ledgerName} | Archive a ledger definition |
| [**v1UsersLedgersLedgerNameGet**](DefaultApi.md#v1UsersLedgersLedgerNameGet) | **GET** /v1/users/ledgers/{ledgerName} | Get ledger definition |
| [**v1UsersLedgersLedgerNameInstancesGet**](DefaultApi.md#v1UsersLedgersLedgerNameInstancesGet) | **GET** /v1/users/ledgers/{ledgerName}/instances | List ledger instances |
| [**v1UsersLedgersLedgerNameInstancesScopeValueDelete**](DefaultApi.md#v1UsersLedgersLedgerNameInstancesScopeValueDelete) | **DELETE** /v1/users/ledgers/{ledgerName}/instances/{scopeValue} | Delete ledger instance |
| [**v1UsersLedgersLedgerNameInstancesScopeValueGet**](DefaultApi.md#v1UsersLedgersLedgerNameInstancesScopeValueGet) | **GET** /v1/users/ledgers/{ledgerName}/instances/{scopeValue} | Get ledger instance |
| [**v1UsersLedgersLedgerNameInstancesScopeValuePut**](DefaultApi.md#v1UsersLedgersLedgerNameInstancesScopeValuePut) | **PUT** /v1/users/ledgers/{ledgerName}/instances/{scopeValue} | Set the ledger instance data |
| [**v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet**](DefaultApi.md#v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet) | **GET** /v1/users/ledgers/{ledgerName}/instances/{scopeValue}/versions | List ledger instance versions |
| [**v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet**](DefaultApi.md#v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet) | **GET** /v1/users/ledgers/{ledgerName}/instances/{scopeValue}/versions/{version} | Get ledger instance version |
| [**v1UsersLedgersLedgerNamePut**](DefaultApi.md#v1UsersLedgersLedgerNamePut) | **PUT** /v1/users/ledgers/{ledgerName} | Update ledger definition |
| [**v1UsersLedgersPost**](DefaultApi.md#v1UsersLedgersPost) | **POST** /v1/users/ledgers | Create a new ledger definition |
| [**v1UsersLogicExecutePost**](DefaultApi.md#v1UsersLogicExecutePost) | **POST** /v1/users/logic/execute | Execute logic function |
| [**v1UsersLogicFunctionsGet**](DefaultApi.md#v1UsersLogicFunctionsGet) | **GET** /v1/users/logic/functions | List logic functions |
| [**v1UsersLogicFunctionsLogicFunctionIdDelete**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdDelete) | **DELETE** /v1/users/logic/functions/{logicFunctionId} | Delete logic function |
| [**v1UsersLogicFunctionsLogicFunctionIdGet**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdGet) | **GET** /v1/users/logic/functions/{logicFunctionId} | Get logic function |
| [**v1UsersLogicFunctionsLogicFunctionIdPut**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdPut) | **PUT** /v1/users/logic/functions/{logicFunctionId} | Update logic function |
| [**v1UsersLogicFunctionsLogicFunctionIdRunsGet**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdRunsGet) | **GET** /v1/users/logic/functions/{logicFunctionId}/runs | List logic functions runs |
| [**v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet) | **GET** /v1/users/logic/functions/{logicFunctionId}/runs/{logicRunId} | Get logic function run |
| [**v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet) | **GET** /v1/users/logic/functions/{logicFunctionId}/runs/{logicRunId}/logs | Get logic function run logs |
| [**v1UsersLogicFunctionsLogicFunctionIdStatsGet**](DefaultApi.md#v1UsersLogicFunctionsLogicFunctionIdStatsGet) | **GET** /v1/users/logic/functions/{logicFunctionId}/stats | Get logic function stats |
| [**v1UsersLogicFunctionsPost**](DefaultApi.md#v1UsersLogicFunctionsPost) | **POST** /v1/users/logic/functions | Create a new logic function |


<a id="callFunction"></a>
# **callFunction**
> DeviceFunctionResponse callFunction(deviceId, functionName, productIdOrSlug, requestBody)

Call a function

Call a function exposed by the device, with arguments passed in the request body. Functions can be called on a device you own, or for any device that is part of a product you are a team member of.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | 
val functionName : kotlin.String = string // kotlin.String | 
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug. Product endpoint only.
val requestBody : kotlin.collections.Map<kotlin.String, kotlin.String> = {
  "arg": "string",
  "format": "string"
} // kotlin.collections.Map<kotlin.String, kotlin.String> | 
try {
    val result : DeviceFunctionResponse = apiInstance.callFunction(deviceId, functionName, productIdOrSlug, requestBody)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#callFunction")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#callFunction")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**|  | |
| **functionName** | **kotlin.String**|  | |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **requestBody** | [**kotlin.collections.Map&lt;kotlin.String, kotlin.String&gt;**](kotlin.String.md)|  | |

### Return type

[**DeviceFunctionResponse**](DeviceFunctionResponse.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="getDevice"></a>
# **getDevice**
> kotlin.String getDevice(deviceId)

Get device information

Get basic information about the given device, including the custom variables and functions it has exposed. This can be called for sandbox devices claimed to your account and for product devices you have access to, regardless of claiming.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : Device = string // Device | Device ID
try {
    val result : kotlin.String = apiInstance.getDevice(deviceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getDevice")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getDevice")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceId** | [**Device**](.md)| Device ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getDevices"></a>
# **getDevices**
> kotlin.collections.List&lt;Device&gt; getDevices()

List devices

List devices the currently authenticated user has access to. By default, devices will be sorted by last_handshake_at in descending order.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.collections.List<Device> = apiInstance.getDevices()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getDevices")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getDevices")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Device&gt;**](Device.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getVariableValue"></a>
# **getVariableValue**
> DeviceVariableResult getVariableValue(deviceId, varName, productIdOrSlug, format)

Get a variable value

Request the current value of a variable exposed by the device. Variables can be read on a device you own, or for any device that is part of a product you are a team member of.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val varName : kotlin.String = string // kotlin.String | Variable name
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug. Product endpoint only.
val format : kotlin.String = string // kotlin.String | Specify raw if you just the value returned
try {
    val result : DeviceVariableResult = apiInstance.getVariableValue(deviceId, varName, productIdOrSlug, format)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getVariableValue")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getVariableValue")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| **varName** | **kotlin.String**| Variable name | |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **format** | **kotlin.String**| Specify raw if you just the value returned | [optional] |

### Return type

[**DeviceVariableResult**](DeviceVariableResult.md)

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="oauthTokenPost"></a>
# **oauthTokenPost**
> kotlin.String oauthTokenPost(contentType, authorization, grantType, clientId, clientSecret, expiresIn, expiresAt, scope)

Generate a customer scoped access token

Creates a token scoped to a customer for your organization.   You must give a valid product OAuth client ID and secret in HTTP Basic Auth or in the client_id and client_secret parameters.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val contentType : kotlin.String = application/x-www-form-urlencoded // kotlin.String | Must be set to application/x-www-form-urlencoded
val authorization : kotlin.String = String // kotlin.String | HTTP Basic Auth where username is the OAuth client ID and password is the OAuth client secret.
val grantType : kotlin.String = grantType_example // kotlin.String | 
val clientId : kotlin.String = clientId_example // kotlin.String | 
val clientSecret : kotlin.String = clientSecret_example // kotlin.String | 
val expiresIn : kotlin.String = expiresIn_example // kotlin.String | 
val expiresAt : kotlin.String = expiresAt_example // kotlin.String | 
val scope : kotlin.String = scope_example // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.oauthTokenPost(contentType, authorization, grantType, clientId, clientSecret, expiresIn, expiresAt, scope)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#oauthTokenPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#oauthTokenPost")
    e.printStackTrace()
}
```

### Parameters
| **contentType** | **kotlin.String**| Must be set to application/x-www-form-urlencoded | |
| **authorization** | **kotlin.String**| HTTP Basic Auth where username is the OAuth client ID and password is the OAuth client secret. | |
| **grantType** | **kotlin.String**|  | |
| **clientId** | **kotlin.String**|  | [optional] |
| **clientSecret** | **kotlin.String**|  | [optional] |
| **expiresIn** | **kotlin.String**|  | [optional] |
| **expiresAt** | **kotlin.String**|  | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **scope** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: application/json

<a id="userGet"></a>
# **userGet**
> kotlin.String userGet()

Get user

Return the user resource for the currently authenticated user.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.userGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#userGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#userGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="userPasswordResetPost"></a>
# **userPasswordResetPost**
> kotlin.String userPasswordResetPost(body)

Forgot password

Create a new password reset token and send the user an email with the token. This endpoint is rate-limited to prevent abuse.   Note: This endpoint does not require an access token.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val body : kotlin.String = {
  "username": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.userPasswordResetPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#userPasswordResetPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#userPasswordResetPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="userPut"></a>
# **userPut**
> kotlin.String userPut(body)

Delete user

Delete the logged-in user. Allows removing user account and artifacts from Particle system

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val body : kotlin.String = {
  "password": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.userPut(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#userPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#userPut")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1AccessTokensCurrentDelete"></a>
# **v1AccessTokensCurrentDelete**
> kotlin.String v1AccessTokensCurrentDelete()

Delete current access token

Delete your currently used token.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.v1AccessTokensCurrentDelete()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1AccessTokensCurrentDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1AccessTokensCurrentDelete")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1AccessTokensCurrentGet"></a>
# **v1AccessTokensCurrentGet**
> kotlin.String v1AccessTokensCurrentGet()

Get the current access token information

Get your currently used token.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.v1AccessTokensCurrentGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1AccessTokensCurrentGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1AccessTokensCurrentGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1AccessTokensDelete"></a>
# **v1AccessTokensDelete**
> kotlin.String v1AccessTokensDelete()

Delete all active access tokens

Delete all your active access tokens.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.v1AccessTokensDelete()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1AccessTokensDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1AccessTokensDelete")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1AccessTokensGet"></a>
# **v1AccessTokensGet**
> kotlin.String v1AccessTokensGet(authorization, otp)

List access tokens

Retrieve a list of all the issued access tokens for your account   Note: Pass your Particle username and password using HTTP Basic Auth.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val authorization : kotlin.String = String // kotlin.String | Your Particle username and password
val otp : kotlin.String = string // kotlin.String | Token given from your MFA device. Usually 6 digits long
try {
    val result : kotlin.String = apiInstance.v1AccessTokensGet(authorization, otp)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1AccessTokensGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1AccessTokensGet")
    e.printStackTrace()
}
```

### Parameters
| **authorization** | **kotlin.String**| Your Particle username and password | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **otp** | **kotlin.String**| Token given from your MFA device. Usually 6 digits long | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1AccessTokensTokenDelete"></a>
# **v1AccessTokensTokenDelete**
> kotlin.String v1AccessTokensTokenDelete(token)

Delete an access token

Delete your unused or lost tokens.   DEPRECATED. Use the DELETE /v1/access_tokens/current endpoint instead.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val token : kotlin.String = string // kotlin.String | Access Token to delete
try {
    val result : kotlin.String = apiInstance.v1AccessTokensTokenDelete(token)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1AccessTokensTokenDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1AccessTokensTokenDelete")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **token** | **kotlin.String**| Access Token to delete | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1BinariesPost"></a>
# **v1BinariesPost**
> kotlin.String v1BinariesPost(body)

Compile source code

Compile source code into a binary for a device

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val body : kotlin.String = {
  "file": "string",
  "platform_id": 0,
  "product_id": "string",
  "build_target_version": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1BinariesPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1BinariesPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1BinariesPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1BuildTargetsGet"></a>
# **v1BuildTargetsGet**
> kotlin.String v1BuildTargetsGet(featured)

List firmware build targets

Lists the firmware versions for all platforms that can be used as build targets during firmware compilation.   Note: This endpoint does not require an access token.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val featured : kotlin.String = false // kotlin.String | When true, show most relevant (featured) build targets only.
try {
    val result : kotlin.String = apiInstance.v1BuildTargetsGet(featured)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1BuildTargetsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1BuildTargetsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **featured** | **kotlin.String**| When true, show most relevant (featured) build targets only. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ClientsClientIdDelete"></a>
# **v1ClientsClientIdDelete**
> kotlin.String v1ClientsClientIdDelete(clientId, productIdOrSlug)

Delete a client

Remove an OAuth client

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val clientId : kotlin.String = string // kotlin.String | The client ID to delete
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
try {
    val result : kotlin.String = apiInstance.v1ClientsClientIdDelete(clientId, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ClientsClientIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ClientsClientIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **clientId** | **kotlin.String**| The client ID to delete | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ClientsClientIdPut"></a>
# **v1ClientsClientIdPut**
> kotlin.String v1ClientsClientIdPut(clientId, productIdOrSlug, body)

Update a client

Update the name or scope of an existing OAuth client.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val clientId : kotlin.String = string // kotlin.String | The client ID to update
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
val body : kotlin.String = {
  "name": "string",
  "scope": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ClientsClientIdPut(clientId, productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ClientsClientIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ClientsClientIdPut")
    e.printStackTrace()
}
```

### Parameters
| **clientId** | **kotlin.String**| The client ID to update | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ClientsGet"></a>
# **v1ClientsGet**
> kotlin.String v1ClientsGet(productIdOrSlug)

List clients

Get a list of all existing OAuth clients, either owned by the authenticated user or clients associated with a product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
try {
    val result : kotlin.String = apiInstance.v1ClientsGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ClientsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ClientsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ClientsPost"></a>
# **v1ClientsPost**
> kotlin.String v1ClientsPost(productIdOrSlug, body)

Create a client

Create an OAuth client that represents an app.   Use type&#x3D;installed for most web and mobile apps. If you want to have Particle users login to their account on Particle in order to give your app access to their devices, then you can go through the full OAuth authorization code grant flow using type&#x3D;web. This is the same way you authorize it is similar to the way you give any app access to your Facebook or Twitter account.   Your client secret will never be displayed again! Save it in a safe place.   If you use type&#x3D;web then you will also need to pass a redirect_uri parameter in the POST body. This is the URL where users will be redirected after telling Particle they are willing to give your app access to their devices.   The scopes provided only contain the object and action parts, skipping the domain which is being infered from the context.   If you are building a web or mobile application for your Particle product, you should use the product-specific endpoint for creating a client (POST /v1/products/:productIdOrSlug/clients). This will grant this client (and access tokens generated by this client) access to product-specific behaviors like calling functions and checking variables on product devices, creating customers, and generating customer scoped access tokens.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
val body : kotlin.String = {
  "name": "string",
  "type": "string",
  "redirect_uri": "string",
  "scope": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ClientsPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ClientsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ClientsPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1DevicesDeviceIdDelete"></a>
# **v1DevicesDeviceIdDelete**
> kotlin.String v1DevicesDeviceIdDelete(deviceId, productIdOrSlug)

Unclaim device

Remove ownership of a device. This will unclaim regardless if the device is owned by a user or a customer, in the case of a product.   When using this endpoint to unclaim a product device, the route looks slightly different:   DELETE /v1/products/:productIdOrSlug/devices/:deviceId/owner   Note the /owner at the end of the route.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | ID of the device to be unclaimed
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
try {
    val result : kotlin.String = apiInstance.v1DevicesDeviceIdDelete(deviceId, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesDeviceIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesDeviceIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| ID of the device to be unclaimed | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1DevicesDeviceIdEventsEventPrefixGet"></a>
# **v1DevicesDeviceIdEventsEventPrefixGet**
> kotlin.String v1DevicesDeviceIdEventsEventPrefixGet(deviceId, eventPrefix)

Get a stream of events for a device

Open a stream of Server Sent Events for all events for the specified device.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val eventPrefix : kotlin.String = string // kotlin.String | Filters the stream to only events starting with the specified prefix
try {
    val result : kotlin.String = apiInstance.v1DevicesDeviceIdEventsEventPrefixGet(deviceId, eventPrefix)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesDeviceIdEventsEventPrefixGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesDeviceIdEventsEventPrefixGet")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **eventPrefix** | **kotlin.String**| Filters the stream to only events starting with the specified prefix | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1DevicesDeviceIdPingPut"></a>
# **v1DevicesDeviceIdPingPut**
> kotlin.String v1DevicesDeviceIdPingPut(deviceId, productIdOrSlug, contentType)

Ping a device

This will ping a device, enabling you to see if your device is online or offline

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | 
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug. Product endpoint only.
val contentType : kotlin.String = application/json // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1DevicesDeviceIdPingPut(deviceId, productIdOrSlug, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesDeviceIdPingPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesDeviceIdPingPut")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**|  | |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**|  | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1DevicesDeviceIdPut"></a>
# **v1DevicesDeviceIdPut**
> kotlin.String v1DevicesDeviceIdPut(deviceId, body)

Flash a device with a bundle

Update the device firmware from a bundle

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val body : kotlin.String = {
  "file": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1DevicesDeviceIdPut(deviceId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesDeviceIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesDeviceIdPut")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1DevicesEventsEventPrefixGet"></a>
# **v1DevicesEventsEventPrefixGet**
> kotlin.String v1DevicesEventsEventPrefixGet(eventPrefix)

Get a stream of your events

Open a stream of Server Sent Events for all events for your devices.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val eventPrefix : kotlin.String = string // kotlin.String | Filters the stream to only events starting with the specified prefix. Omit to get all events.
try {
    val result : kotlin.String = apiInstance.v1DevicesEventsEventPrefixGet(eventPrefix)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesEventsEventPrefixGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesEventsEventPrefixGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **eventPrefix** | **kotlin.String**| Filters the stream to only events starting with the specified prefix. Omit to get all events. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1DevicesEventsPost"></a>
# **v1DevicesEventsPost**
> kotlin.String v1DevicesEventsPost(body)

Publish an event

Publish an event

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val body : kotlin.String = {
  "name": "string",
  "data": "string",
  "ttl": 0
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1DevicesEventsPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesEventsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesEventsPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1DevicesPost"></a>
# **v1DevicesPost**
> kotlin.String v1DevicesPost(body)

Claim a device

Claim a new or unclaimed device to your account.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val body : kotlin.String = {
  "id": "string",
  "request_transfer": false
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1DevicesPost(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DevicesPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DevicesPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1DiagnosticsDeviceIdGet"></a>
# **v1DiagnosticsDeviceIdGet**
> kotlin.String v1DiagnosticsDeviceIdGet(deviceId, productIdOrSlug, accept, startDate, endDate)

Get all historical device vitals

Returns all stored device vital records sent by the device to the Device Cloud. Device vitals records will expire after 1 month.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
val accept : kotlin.String = String // kotlin.String | Optionally set to text/csv to return historic device vitals as a CSV
val startDate : kotlin.String = 2024-10-07T10:03:32.032Z // kotlin.String | Oldest diagnostic to return, inclusive. Date in ISO8601 format.
val endDate : kotlin.String = 2024-10-07T10:03:32.032Z // kotlin.String | Newest diagnostic to return, exclusive. Date in ISO8601 format.
try {
    val result : kotlin.String = apiInstance.v1DiagnosticsDeviceIdGet(deviceId, productIdOrSlug, accept, startDate, endDate)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DiagnosticsDeviceIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DiagnosticsDeviceIdGet")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |
| **accept** | **kotlin.String**| Optionally set to text/csv to return historic device vitals as a CSV | |
| **startDate** | **kotlin.String**| Oldest diagnostic to return, inclusive. Date in ISO8601 format. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **endDate** | **kotlin.String**| Newest diagnostic to return, exclusive. Date in ISO8601 format. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1DiagnosticsDeviceIdLastGet"></a>
# **v1DiagnosticsDeviceIdLastGet**
> kotlin.String v1DiagnosticsDeviceIdLastGet(deviceId, productIdOrSlug)

Get last known device vitals

Returns the last device vitals payload sent by the device to the Device Cloud.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
try {
    val result : kotlin.String = apiInstance.v1DiagnosticsDeviceIdLastGet(deviceId, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DiagnosticsDeviceIdLastGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DiagnosticsDeviceIdLastGet")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1DiagnosticsDeviceIdUpdatePost"></a>
# **v1DiagnosticsDeviceIdUpdatePost**
> kotlin.String v1DiagnosticsDeviceIdUpdatePost(deviceId, productIdOrSlug, contentType)

Refresh device vitals

Refresh diagnostic vitals for a single device. This will instruct the device to publish a new event to the Device Cloud containing a device vitals payload. This is an asynchronous request: the HTTP request returns immediately after the request to the device is sent. In order for the device to respond with a vitals payload, it must be online and connected to the Device Cloud.   The device will respond by publishing an event named spark/device/diagnostics/update. See the description of the device vitals event.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
val contentType : kotlin.String = application/json // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1DiagnosticsDeviceIdUpdatePost(deviceId, productIdOrSlug, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1DiagnosticsDeviceIdUpdatePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1DiagnosticsDeviceIdUpdatePost")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**|  | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1EventsEventPrefixGet"></a>
# **v1EventsEventPrefixGet**
> kotlin.String v1EventsEventPrefixGet(eventPrefix)

Get a stream of events

Open a stream of Server Sent Events for all events. for your devices matching the filter.   Note that as of April 2018, the event prefix filter is required. It was optional before.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val eventPrefix : kotlin.String = string // kotlin.String | Filters the stream to only events starting with the specified prefix. The event prefix filter is required for this endpoint.
try {
    val result : kotlin.String = apiInstance.v1EventsEventPrefixGet(eventPrefix)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1EventsEventPrefixGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1EventsEventPrefixGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **eventPrefix** | **kotlin.String**| Filters the stream to only events starting with the specified prefix. The event prefix filter is required for this endpoint. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1IntegrationsGet"></a>
# **v1IntegrationsGet**
> kotlin.String v1IntegrationsGet(productIdOrSlug)

List integrations

List all integrations. Pay special attention to the integration_type attribute of each integration, which will let you know whether the integration is a Webhook, an Azure IoT Hub integration, or a Google Cloud Platform integration.   If you would like to only list webhooks (integrations with type: &#39;Webhook&#39;), you can use a slightly different endpoint:   GET /v1/webhooks

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
try {
    val result : kotlin.String = apiInstance.v1IntegrationsGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1IntegrationsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1IntegrationsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1IntegrationsIntegrationIdDelete"></a>
# **v1IntegrationsIntegrationIdDelete**
> kotlin.String v1IntegrationsIntegrationIdDelete(integrationId, productIdOrSlug)

Delete an integration

Delete an integration and immediate stop it from triggering. The integration can belong to a user or to a product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val integrationId : kotlin.String = string // kotlin.String | The ID of the desired integration
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug (only for product webhooks)
try {
    val result : kotlin.String = apiInstance.v1IntegrationsIntegrationIdDelete(integrationId, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1IntegrationsIntegrationIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1IntegrationsIntegrationIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **integrationId** | **kotlin.String**| The ID of the desired integration | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug (only for product webhooks) | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1IntegrationsIntegrationIdGet"></a>
# **v1IntegrationsIntegrationIdGet**
> kotlin.String v1IntegrationsIntegrationIdGet(integrationId, productIdOrSlug)

Get integration

Get a single integration. Pay special attention to the integration_type attribute of each integration, which will let you know whether the integration is a Webhook, an Azure IoT Hub integration, a Google Cloud Platform integration, or a Google Maps integration.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val integrationId : kotlin.String = string // kotlin.String | The ID of the desired integration
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
try {
    val result : kotlin.String = apiInstance.v1IntegrationsIntegrationIdGet(integrationId, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1IntegrationsIntegrationIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1IntegrationsIntegrationIdGet")
    e.printStackTrace()
}
```

### Parameters
| **integrationId** | **kotlin.String**| The ID of the desired integration | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1IntegrationsIntegrationIdPut"></a>
# **v1IntegrationsIntegrationIdPut**
> kotlin.String v1IntegrationsIntegrationIdPut(integrationId, productIdOrSlug, body)

Edit Google Maps Integration

Edit a Google Maps integration. Subsequent triggering of this integration will be sent with the new attributes.   The configuration replaces the entire previous webhook configuration. It does not merge in changes.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val integrationId : kotlin.String = string // kotlin.String | The ID of the desired integration
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
val body : kotlin.String = {
  "event": "string",
  "name": "string",
  "deviceID": "string",
  "api_key": "string",
  "disabled": false
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1IntegrationsIntegrationIdPut(integrationId, productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1IntegrationsIntegrationIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1IntegrationsIntegrationIdPut")
    e.printStackTrace()
}
```

### Parameters
| **integrationId** | **kotlin.String**| The ID of the desired integration | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1IntegrationsIntegrationIdTestPost"></a>
# **v1IntegrationsIntegrationIdTestPost**
> kotlin.String v1IntegrationsIntegrationIdTestPost(integrationId, productIdOrSlug, body)

Test an integration

Send a test event that triggers the integration. Helps build confidence that an integration is configured properly.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val integrationId : kotlin.String = string // kotlin.String | The ID of the desired integration
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
val body : kotlin.String = {
  "data": "string",
  "device_id": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1IntegrationsIntegrationIdTestPost(integrationId, productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1IntegrationsIntegrationIdTestPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1IntegrationsIntegrationIdTestPost")
    e.printStackTrace()
}
```

### Parameters
| **integrationId** | **kotlin.String**| The ID of the desired integration | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1IntegrationsPost"></a>
# **v1IntegrationsPost**
> kotlin.String v1IntegrationsPost(productIdOrSlug, body)

Enable Google Maps integration

Enable an integration with Google Maps. For more details, check out the tutorial.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only.
val body : kotlin.String = {
  "integration_type": "string",
  "event": "string",
  "name": "string",
  "api_key": "string",
  "deviceID": "string",
  "disabled": false
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1IntegrationsPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1IntegrationsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1IntegrationsPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1OrgsGet"></a>
# **v1OrgsGet**
> kotlin.String v1OrgsGet()

List organizations

List organizations the currently authenticated user has access to

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.v1OrgsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1OrgsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1OrgsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1OrgsOrgIdOrSlugGet"></a>
# **v1OrgsOrgIdOrSlugGet**
> kotlin.String v1OrgsOrgIdOrSlugGet(orgIdOrSlug)

Retrieve an organization

Retrieve details for an organization.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug
try {
    val result : kotlin.String = apiInstance.v1OrgsOrgIdOrSlugGet(orgIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1OrgsOrgIdOrSlugGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1OrgsOrgIdOrSlugGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1OrgsOrgIdOrSlugProductsGet"></a>
# **v1OrgsOrgIdOrSlugProductsGet**
> kotlin.String v1OrgsOrgIdOrSlugProductsGet(orgIdOrSlug)

List organization products

List products which belong the the organization

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug
try {
    val result : kotlin.String = apiInstance.v1OrgsOrgIdOrSlugProductsGet(orgIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1OrgsOrgIdOrSlugProductsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1OrgsOrgIdOrSlugProductsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1OrgsOrgIdOrSlugServiceAgreementsGet"></a>
# **v1OrgsOrgIdOrSlugServiceAgreementsGet**
> kotlin.String v1OrgsOrgIdOrSlugServiceAgreementsGet(orgIdOrSlug)

Get organization service agreements

Get the service agreements related to an organization

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Org ID or Slug
try {
    val result : kotlin.String = apiInstance.v1OrgsOrgIdOrSlugServiceAgreementsGet(orgIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1OrgsOrgIdOrSlugServiceAgreementsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1OrgsOrgIdOrSlugServiceAgreementsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **orgIdOrSlug** | **kotlin.String**| Org ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost"></a>
# **v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost**
> kotlin.String v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost(orgSlugOrId, serviceAgreementId, body)

Create an org usage report

Request a new usage report related to the organization service agreement.   The usage report will be processed asynchronously. Expect its \&quot;state\&quot; to change throughout time.   The user must be an active member of the organization.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgSlugOrId : kotlin.String = string // kotlin.String | Organization Slug or ID
val serviceAgreementId : kotlin.String =  // kotlin.String | Service Agreement ID
val body : kotlin.String = {
  "report_type": "string",
  "date_period_start": "string",
  "date_period_end": "string",
  "devices": [
    "string",
    "string"
  ],
  "products": [
    "string",
    "string"
  ]
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost(orgSlugOrId, serviceAgreementId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1OrgsOrgSlugOrIdServiceAgreementsServiceAgreementIdUsageReportsPost")
    e.printStackTrace()
}
```

### Parameters
| **orgSlugOrId** | **kotlin.String**| Organization Slug or ID | |
| **serviceAgreementId** | **kotlin.String**| Service Agreement ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet"></a>
# **v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet**
> kotlin.String v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet(usageReportId, orgSlugOrId)

Get an org usage report

Get a single usage report related to an organization. Expect \&quot;download_url\&quot; to be present only when the usage report has an \&quot;available\&quot; state.   The user must be an active member of the organization.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val usageReportId : kotlin.String =  // kotlin.String | The usage report ID
val orgSlugOrId : kotlin.String = string // kotlin.String | Organization Slug or ID
try {
    val result : kotlin.String = apiInstance.v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet(usageReportId, orgSlugOrId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1OrgsOrgSlugOrIdUsageReportsUsageReportIdGet")
    e.printStackTrace()
}
```

### Parameters
| **usageReportId** | **kotlin.String**| The usage report ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **orgSlugOrId** | **kotlin.String**| Organization Slug or ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugConfigDelete"></a>
# **v1ProductsProductIdOrSlugConfigDelete**
> kotlin.String v1ProductsProductIdOrSlugConfigDelete(productIdOrSlug, contentType)

Delete product configuration schema

Delete configuration schema, use Tracker Edge defaults.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val contentType : kotlin.String = application/schema+json // kotlin.String | Must be set to \"application/schema+json\" for this endpoint.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugConfigDelete(productIdOrSlug, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**| Must be set to \&quot;application/schema+json\&quot; for this endpoint. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugConfigDeviceIdDelete"></a>
# **v1ProductsProductIdOrSlugConfigDeviceIdDelete**
> kotlin.String v1ProductsProductIdOrSlugConfigDeviceIdDelete(productIdOrSlug, deviceId, contentType)

Delete device configuration schema

Delete device&#39;s configuration schema, use product&#39;s.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val deviceId : kotlin.String = string // kotlin.String | The device ID to delete the schema.
val contentType : kotlin.String = application/schema+json // kotlin.String | Must be set to \"application/schema+json\" for this endpoint.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugConfigDeviceIdDelete(productIdOrSlug, deviceId, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDeviceIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDeviceIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **deviceId** | **kotlin.String**| The device ID to delete the schema. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**| Must be set to \&quot;application/schema+json\&quot; for this endpoint. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugConfigDeviceIdGet"></a>
# **v1ProductsProductIdOrSlugConfigDeviceIdGet**
> kotlin.String v1ProductsProductIdOrSlugConfigDeviceIdGet(productIdOrSlug, deviceId, accept)

Get device schema

Get the possible values that can be configured for one device in this product, in JSON Schema format

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val deviceId : kotlin.String = string // kotlin.String | The device ID to query.
val accept : kotlin.String = application/schema+json // kotlin.String | Must be set to \"application/schema+json\" for this endpoint
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugConfigDeviceIdGet(productIdOrSlug, deviceId, accept)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDeviceIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDeviceIdGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **deviceId** | **kotlin.String**| The device ID to query. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **accept** | **kotlin.String**| Must be set to \&quot;application/schema+json\&quot; for this endpoint | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugConfigDeviceIdPut"></a>
# **v1ProductsProductIdOrSlugConfigDeviceIdPut**
> kotlin.String v1ProductsProductIdOrSlugConfigDeviceIdPut(productIdOrSlug, deviceId, contentType)

Set device configuration schema

Set configuration schema for the device.   This must be the entire schema, including the standard Particle parts; there is no merging of changes.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val deviceId : kotlin.String = string // kotlin.String | Device ID
val contentType : kotlin.String = application/schema+json // kotlin.String | Must be set to \"application/schema+json\" for this endpoint.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugConfigDeviceIdPut(productIdOrSlug, deviceId, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDeviceIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigDeviceIdPut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **deviceId** | **kotlin.String**| Device ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**| Must be set to \&quot;application/schema+json\&quot; for this endpoint. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugConfigGet"></a>
# **v1ProductsProductIdOrSlugConfigGet**
> kotlin.String v1ProductsProductIdOrSlugConfigGet(productIdOrSlug, accept)

Get product schema

Get the possible values that can be configured for this product, in JSON Schema format

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val accept : kotlin.String = application/schema+json // kotlin.String | Must be set to \"application/schema+json\" for this endpoint
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugConfigGet(productIdOrSlug, accept)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **accept** | **kotlin.String**| Must be set to \&quot;application/schema+json\&quot; for this endpoint | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugConfigPut"></a>
# **v1ProductsProductIdOrSlugConfigPut**
> kotlin.String v1ProductsProductIdOrSlugConfigPut(productIdOrSlug, contentType)

Set product configuration schema

Set configuration schema that will become the default for the product.   This must be the entire schema, including the standard Particle parts; there is no merging of changes.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val contentType : kotlin.String = application/schema+json // kotlin.String | Must be set to \"application/schema+json\" for this endpoint.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugConfigPut(productIdOrSlug, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugConfigPut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**| Must be set to \&quot;application/schema+json\&quot; for this endpoint. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugCustomersCustomerEmailDelete"></a>
# **v1ProductsProductIdOrSlugCustomersCustomerEmailDelete**
> kotlin.String v1ProductsProductIdOrSlugCustomersCustomerEmailDelete(productIdOrSlug, customerEmail)

Delete a customer

Delete a customer in a product. Will also revoke all of this customer&#39;s access tokens, pending device claim codes and activation codes.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val customerEmail : kotlin.String = string // kotlin.String | Email of the customer account that you'd like to remove
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugCustomersCustomerEmailDelete(productIdOrSlug, customerEmail)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersCustomerEmailDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersCustomerEmailDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **customerEmail** | **kotlin.String**| Email of the customer account that you&#39;d like to remove | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugCustomersCustomerEmailPut"></a>
# **v1ProductsProductIdOrSlugCustomersCustomerEmailPut**
> kotlin.String v1ProductsProductIdOrSlugCustomersCustomerEmailPut(productIdOrSlug, customerEmail, body)

Update customer password

Update the account password for a customer. Only relevant for non-shadow customers that have a password saved in Particle&#39;s system. Must be called with an access token that has access to the product, not a customer access token.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val customerEmail : kotlin.String = string // kotlin.String | Email of the customer account that you'd like to update
val body : kotlin.String = {
  "password": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugCustomersCustomerEmailPut(productIdOrSlug, customerEmail, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersCustomerEmailPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersCustomerEmailPut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| **customerEmail** | **kotlin.String**| Email of the customer account that you&#39;d like to update | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugCustomersGet"></a>
# **v1ProductsProductIdOrSlugCustomersGet**
> kotlin.String v1ProductsProductIdOrSlugCustomersGet(productIdOrSlug)

List customers for a product

List Customers for a product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugCustomersGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugCustomersPost"></a>
# **v1ProductsProductIdOrSlugCustomersPost**
> kotlin.String v1ProductsProductIdOrSlugCustomersPost(productIdOrSlug, authorization, body)

Create a customer - Implicit

Create a customer for a product using OAuth implicit grant type. This is the way you should hit the POST customers endpoint if you are creating customers from a web browser. After a successful POST, the customer access token will be appended as a hash to the redirect URI associated with the client credentials provided. For this grant type, you must also pass response_type: token.   You must give a valid product OAuth client ID in HTTP Basic Auth or in the client_id parameter. Do not pass the OAuth client secret when creating customers from a web browser.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val authorization : kotlin.String = String // kotlin.String | HTTP Basic Auth where username is the OAuth client ID and password is blank.
val body : kotlin.String = {
  "client_id": "string",
  "email": "string",
  "password": "string",
  "response_type": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugCustomersPost(productIdOrSlug, authorization, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugCustomersPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| **authorization** | **kotlin.String**| HTTP Basic Auth where username is the OAuth client ID and password is blank. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugDevicesDeviceIdDelete"></a>
# **v1ProductsProductIdOrSlugDevicesDeviceIdDelete**
> kotlin.String v1ProductsProductIdOrSlugDevicesDeviceIdDelete(productIdOrSlug, deviceId)

Deny a quarantined device

Deny a quarantined device

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val deviceId : kotlin.String = string // kotlin.String | ID of the device to be denied
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugDevicesDeviceIdDelete(productIdOrSlug, deviceId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesDeviceIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesDeviceIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceId** | **kotlin.String**| ID of the device to be denied | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugDevicesDeviceIdGet"></a>
# **v1ProductsProductIdOrSlugDevicesDeviceIdGet**
> kotlin.String v1ProductsProductIdOrSlugDevicesDeviceIdGet(deviceId, productIdOrSlug)

Get product device information

Get basic information about a given device that is part of a product   See Get device information for the response attributes

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugDevicesDeviceIdGet(deviceId, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesDeviceIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesDeviceIdGet")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugDevicesDeviceIdPut"></a>
# **v1ProductsProductIdOrSlugDevicesDeviceIdPut**
> kotlin.String v1ProductsProductIdOrSlugDevicesDeviceIdPut(deviceId, productIdOrSlug, body)

Assign groups to a device

Update group memberships for an individual device. This is an absolute endpoint, meaning that regardless of previous group memberships, the group names passed to this endpoint will be the ones assigned to the device.   If you pass a group name that does not yet exist, it will be created and assigned to the device.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val deviceId : kotlin.String = string // kotlin.String | Device ID
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val body : kotlin.String = {
  "groups": [
    {
      "key": "value"
    },
    {
      "key": "value"
    }
  ]
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugDevicesDeviceIdPut(deviceId, productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesDeviceIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesDeviceIdPut")
    e.printStackTrace()
}
```

### Parameters
| **deviceId** | **kotlin.String**| Device ID | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugDevicesGet"></a>
# **v1ProductsProductIdOrSlugDevicesGet**
> kotlin.String v1ProductsProductIdOrSlugDevicesGet(productIdOrSlug, deviceId, groups, deviceName, serialNumber, sortAttr, sortDir, quarantined, page, perPage)

List devices in a product

List all devices that are part of a product. Results are paginated, by default returns 25 device records per page.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val deviceId : kotlin.String = string // kotlin.String | Filter results to devices with this ID (partial matching)
val groups : kotlin.String = string // kotlin.String | Comma separated list of full group names to filter results to devices belonging to these groups only
val deviceName : kotlin.String = string // kotlin.String | Filter results to devices with this name (partial matching)
val serialNumber : kotlin.String = string // kotlin.String | Filter results to devices with this serial number (partial matching)
val sortAttr : kotlin.String = string // kotlin.String | The attribute by which to sort results. Options for sorting are deviceName, deviceId, firmwareVersion, or lastConnection. By default, if no sortAttr parameter is set, devices will be sorted by last connection, in descending order
val sortDir : kotlin.String = string // kotlin.String | The direction of sorting. Pass asc for ascending sorting or desc for descending sorting
val quarantined : kotlin.String = false // kotlin.String | include / exclude quarantined devices
val page : kotlin.String = 0 // kotlin.String | Current page of results
val perPage : kotlin.String = 0 // kotlin.String | Records per page
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugDevicesGet(productIdOrSlug, deviceId, groups, deviceName, serialNumber, sortAttr, sortDir, quarantined, page, perPage)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| **deviceId** | **kotlin.String**| Filter results to devices with this ID (partial matching) | [optional] |
| **groups** | **kotlin.String**| Comma separated list of full group names to filter results to devices belonging to these groups only | [optional] |
| **deviceName** | **kotlin.String**| Filter results to devices with this name (partial matching) | [optional] |
| **serialNumber** | **kotlin.String**| Filter results to devices with this serial number (partial matching) | [optional] |
| **sortAttr** | **kotlin.String**| The attribute by which to sort results. Options for sorting are deviceName, deviceId, firmwareVersion, or lastConnection. By default, if no sortAttr parameter is set, devices will be sorted by last connection, in descending order | [optional] |
| **sortDir** | **kotlin.String**| The direction of sorting. Pass asc for ascending sorting or desc for descending sorting | [optional] |
| **quarantined** | **kotlin.String**| include / exclude quarantined devices | [optional] |
| **page** | **kotlin.String**| Current page of results | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **perPage** | **kotlin.String**| Records per page | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugDevicesPost"></a>
# **v1ProductsProductIdOrSlugDevicesPost**
> kotlin.String v1ProductsProductIdOrSlugDevicesPost(productIdOrSlug, body)

Approve a quarantined device

Approve a quarantined device. This will immediately release the device from quarantine and allow it to publish events, receive firmware updates, etc.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val body : kotlin.String = {
  "id": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugDevicesPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugDevicesPut"></a>
# **v1ProductsProductIdOrSlugDevicesPut**
> kotlin.String v1ProductsProductIdOrSlugDevicesPut(productIdOrSlug, body)

Batch assign groups to devices

Assign groups to devices in a product as a batch action. Groups can either be added or removed from all devices passed to the endpoint.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val body : kotlin.String = {
  "action": "string",
  "devices": [
    {
      "key": "value"
    },
    {
      "key": "value"
    }
  ],
  "metadata": {
    "key": "value",
    "add": [
      {
        "key": "value"
      },
      {
        "key": "value"
      }
    ],
    "remove": [
      {
        "key": "value"
      },
      {
        "key": "value"
      }
    ]
  }
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugDevicesPut(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugDevicesPut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugEventsEventPrefixGet"></a>
# **v1ProductsProductIdOrSlugEventsEventPrefixGet**
> kotlin.String v1ProductsProductIdOrSlugEventsEventPrefixGet(productIdOrSlug, eventPrefix)

Product event stream

Open a stream of Server Sent Events for all events for a product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val eventPrefix : kotlin.String = string // kotlin.String | Filters the stream to only events starting with the specified prefix
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugEventsEventPrefixGet(productIdOrSlug, eventPrefix)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugEventsEventPrefixGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugEventsEventPrefixGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **eventPrefix** | **kotlin.String**| Filters the stream to only events starting with the specified prefix | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugEventsPost"></a>
# **v1ProductsProductIdOrSlugEventsPost**
> kotlin.String v1ProductsProductIdOrSlugEventsPost(productIdOrSlug, body)

Publish a product event

Publish an event that is sent to the product&#39;s event stream

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val body : kotlin.String = {
  "name": "string",
  "data": "string",
  "ttl": 0
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugEventsPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugEventsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugEventsPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwareGet"></a>
# **v1ProductsProductIdOrSlugFirmwareGet**
> kotlin.String v1ProductsProductIdOrSlugFirmwareGet(productIdOrSlug)

List all product firmwares

List all versions of product firmware

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwareGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwarePost"></a>
# **v1ProductsProductIdOrSlugFirmwarePost**
> kotlin.String v1ProductsProductIdOrSlugFirmwarePost(productIdOrSlug, body)

Upload product firmware

Upload a new firmware version to a product

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val body : kotlin.String = {
  "version": 0,
  "title": "string",
  "productIdOrSlug": "string",
  "description": "string",
  "mandatory": false
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwarePost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwarePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwarePost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwareReleasePut"></a>
# **v1ProductsProductIdOrSlugFirmwareReleasePut**
> kotlin.String v1ProductsProductIdOrSlugFirmwareReleasePut(productIdOrSlug, body)

Release product firmware

Release a version of firmware to the fleet of product devices. When releasing as the product default, all non-development devices that are not individually locked to a version of product firmware will automatically download and run this version of firmware the next time they handshake with the cloud.   You can also release firmware to specific groups for more fine-grained firmware management.   Note: Before releasing firmware for the first time, the firmware must be running on at least one device in your product fleet that has successfully re-connected to the Particle cloud.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val body : kotlin.String = {
  "version": 0,
  "product_default": false,
  "groups": "string",
  "intelligent": false
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwareReleasePut(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareReleasePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareReleasePut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwareVersionBinaryGet"></a>
# **v1ProductsProductIdOrSlugFirmwareVersionBinaryGet**
> kotlin.String v1ProductsProductIdOrSlugFirmwareVersionBinaryGet(productIdOrSlug, version)

Download firmware binary

Retrieve and download the original binary of a version of product firmware.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val version : kotlin.String =  // kotlin.String | Version number of firmware to retrieve
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwareVersionBinaryGet(productIdOrSlug, version)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionBinaryGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionBinaryGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **version** | **kotlin.String**| Version number of firmware to retrieve | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwareVersionDelete"></a>
# **v1ProductsProductIdOrSlugFirmwareVersionDelete**
> kotlin.String v1ProductsProductIdOrSlugFirmwareVersionDelete(productIdOrSlug, version)

Delete unreleased firmware binary

Delete a version of product firmware that has never been released.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val version : kotlin.String =  // kotlin.String | Version number of firmware to delete
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwareVersionDelete(productIdOrSlug, version)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **version** | **kotlin.String**| Version number of firmware to delete | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwareVersionGet"></a>
# **v1ProductsProductIdOrSlugFirmwareVersionGet**
> kotlin.String v1ProductsProductIdOrSlugFirmwareVersionGet(productIdOrSlug, version)

Get product firmware

Get a specific version of product firmware

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val version : kotlin.String =  // kotlin.String | Version number of firmware to retrieve
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwareVersionGet(productIdOrSlug, version)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **version** | **kotlin.String**| Version number of firmware to retrieve | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFirmwareVersionPut"></a>
# **v1ProductsProductIdOrSlugFirmwareVersionPut**
> kotlin.String v1ProductsProductIdOrSlugFirmwareVersionPut(productIdOrSlug, version, body)

Edit product firmware

Edit a specific product firmware version

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val version : kotlin.String = string // kotlin.String | Version number of the firmware to edit
val body : kotlin.String = {
  "title": "string",
  "description": "string",
  "mandatory": false
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFirmwareVersionPut(productIdOrSlug, version, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFirmwareVersionPut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| **version** | **kotlin.String**| Version number of the firmware to edit | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugFleetLocationsGet"></a>
# **v1ProductsProductIdOrSlugFleetLocationsGet**
> kotlin.String v1ProductsProductIdOrSlugFleetLocationsGet(productIdOrSlug, deviceId, deviceName, groups)

Query fleet-wide locations for devices within a product

Get the latest location data for all the devices in a product to get a fleet-wide summary of device location.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val deviceId : kotlin.String = string // kotlin.String | Device ID prefix to include in the query
val deviceName : kotlin.String = string // kotlin.String | Device name prefix to include in the query
val groups : kotlin.String = string,string // kotlin.String | Array of group names to include in the query
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugFleetLocationsGet(productIdOrSlug, deviceId, deviceName, groups)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugFleetLocationsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugFleetLocationsGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **deviceId** | **kotlin.String**| Device ID prefix to include in the query | [optional] |
| **deviceName** | **kotlin.String**| Device name prefix to include in the query | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **groups** | **kotlin.String**| Array of group names to include in the query | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugGet"></a>
# **v1ProductsProductIdOrSlugGet**
> kotlin.String v1ProductsProductIdOrSlugGet(productIdOrSlug)

Retrieve a product

Retrieve details for a product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugGroupsGet"></a>
# **v1ProductsProductIdOrSlugGroupsGet**
> kotlin.String v1ProductsProductIdOrSlugGroupsGet(productIdOrSlug, name)

List device groups

List the group objects that exist in the product. Optionally, filter by group name (partial match).

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val name : kotlin.String = string // kotlin.String | String to filter group names by. Partial string matching.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugGroupsGet(productIdOrSlug, name)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **name** | **kotlin.String**| String to filter group names by. Partial string matching. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugGroupsGroupNameDelete"></a>
# **v1ProductsProductIdOrSlugGroupsGroupNameDelete**
> kotlin.String v1ProductsProductIdOrSlugGroupsGroupNameDelete(productIdOrSlug, groupName)

Delete device group

Delete device group. All devices that belong to this group will be removed from the deleted group.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val groupName : kotlin.String = string // kotlin.String | The group name to delete
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugGroupsGroupNameDelete(productIdOrSlug, groupName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGroupNameDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGroupNameDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **groupName** | **kotlin.String**| The group name to delete | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugGroupsGroupNameGet"></a>
# **v1ProductsProductIdOrSlugGroupsGroupNameGet**
> kotlin.String v1ProductsProductIdOrSlugGroupsGroupNameGet(productIdOrSlug, groupName)

Get device group

Retrieve full info on a specific product group, including its device count.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val groupName : kotlin.String = string // kotlin.String | The group name to fetch
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugGroupsGroupNameGet(productIdOrSlug, groupName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGroupNameGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGroupNameGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **groupName** | **kotlin.String**| The group name to fetch | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugGroupsGroupNamePut"></a>
# **v1ProductsProductIdOrSlugGroupsGroupNamePut**
> kotlin.String v1ProductsProductIdOrSlugGroupsGroupNamePut(productIdOrSlug, groupName, body)

Edit device group

Edit attributes of a specific device group. You must pass one of name, color, or description.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val groupName : kotlin.String = string // kotlin.String | The group name to edit
val body : kotlin.String = {
  "name": "string",
  "color": "string",
  "description": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugGroupsGroupNamePut(productIdOrSlug, groupName, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGroupNamePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsGroupNamePut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| **groupName** | **kotlin.String**| The group name to edit | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugGroupsPost"></a>
# **v1ProductsProductIdOrSlugGroupsPost**
> kotlin.String v1ProductsProductIdOrSlugGroupsPost(productIdOrSlug, body)

Create device group

Create a new device group withinin a product

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val body : kotlin.String = {
  "name": "string",
  "color": "string",
  "description": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugGroupsPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugGroupsPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugImpactGet"></a>
# **v1ProductsProductIdOrSlugImpactGet**
> kotlin.String v1ProductsProductIdOrSlugImpactGet(productIdOrSlug)

Impact of taking action

Understand the number of devices that would receive an over-the-air update as a result of taking an action related to device groups.   Currently, this endpoint supports understanding the impact of releasing/unreleasing firmware to one or more device groups. Pass edit_groups_for_firmware as the action parameter when calling the endpoint.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugImpactGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugImpactGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugImpactGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugLocationsDeviceIdGet"></a>
# **v1ProductsProductIdOrSlugLocationsDeviceIdGet**
> kotlin.String v1ProductsProductIdOrSlugLocationsDeviceIdGet(productIdOrSlug, deviceId, dateRange, rectBl, rectTr)

Query location for one device within a product

Get last known or historical location data for one device. Date range and bounding box can be specified to narrow down the query. Properties and custom data will be returned by default.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val deviceId : kotlin.String = string // kotlin.String | Device ID
val dateRange : kotlin.String = string // kotlin.String | Start and end date in ISO8601 format, separated by comma, to query. Omitting date_range will return last known location.
val rectBl : kotlin.String = string // kotlin.String | Bottom left of the rectangular bounding box to query. Latitude and longitude separated by comma.
val rectTr : kotlin.String = string // kotlin.String | Top right of the rectangular bounding box to query. Latitude and longitude separated by comma.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugLocationsDeviceIdGet(productIdOrSlug, deviceId, dateRange, rectBl, rectTr)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugLocationsDeviceIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugLocationsDeviceIdGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **deviceId** | **kotlin.String**| Device ID | |
| **dateRange** | **kotlin.String**| Start and end date in ISO8601 format, separated by comma, to query. Omitting date_range will return last known location. | [optional] |
| **rectBl** | **kotlin.String**| Bottom left of the rectangular bounding box to query. Latitude and longitude separated by comma. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **rectTr** | **kotlin.String**| Top right of the rectangular bounding box to query. Latitude and longitude separated by comma. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugLocationsGet"></a>
# **v1ProductsProductIdOrSlugLocationsGet**
> kotlin.String v1ProductsProductIdOrSlugLocationsGet(productIdOrSlug, dateRange, rectBl, rectTr, deviceId, deviceName, groups, page, perPage)

Query location for devices within a product

Get latest or historical location data for devices. Date range and bounding box can be specified to narrow the query.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val dateRange : kotlin.String = string // kotlin.String | Start and end date in ISO8601 format, separated by comma, to query. Omitting date_range will return last known location.
val rectBl : kotlin.String = string // kotlin.String | Bottom left of the rectangular bounding box to query. Latitude and longitude separated by comma.
val rectTr : kotlin.String = string // kotlin.String | Top right of the rectangular bounding box to query. Latitude and longitude separated by comma.
val deviceId : kotlin.String = string // kotlin.String | Device ID prefix to include in the query
val deviceName : kotlin.String = string // kotlin.String | Device name prefix to include in the query
val groups : kotlin.String = string,string // kotlin.String | Array of group names to include in the query
val page : kotlin.String = 0 // kotlin.String | Page of results to display. Defaults to 1
val perPage : kotlin.String = 0 // kotlin.String | Number of results per page. Defaults to 20. Maximum of 100
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugLocationsGet(productIdOrSlug, dateRange, rectBl, rectTr, deviceId, deviceName, groups, page, perPage)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugLocationsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugLocationsGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **dateRange** | **kotlin.String**| Start and end date in ISO8601 format, separated by comma, to query. Omitting date_range will return last known location. | [optional] |
| **rectBl** | **kotlin.String**| Bottom left of the rectangular bounding box to query. Latitude and longitude separated by comma. | [optional] |
| **rectTr** | **kotlin.String**| Top right of the rectangular bounding box to query. Latitude and longitude separated by comma. | [optional] |
| **deviceId** | **kotlin.String**| Device ID prefix to include in the query | [optional] |
| **deviceName** | **kotlin.String**| Device name prefix to include in the query | [optional] |
| **groups** | **kotlin.String**| Array of group names to include in the query | [optional] |
| **page** | **kotlin.String**| Page of results to display. Defaults to 1 | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **perPage** | **kotlin.String**| Number of results per page. Defaults to 20. Maximum of 100 | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugMetricsEventsGet"></a>
# **v1ProductsProductIdOrSlugMetricsEventsGet**
> kotlin.String v1ProductsProductIdOrSlugMetricsEventsGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)

Get event traffic health metrics

Reports each time a device publishes an event in this product.   Each time bucket contains counts of:   - received: the cloud received an event from a device.   Can be filtered to include only events from devices with a certain firmware version, Device OS version or in a certain group.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val startDate : kotlin.String = 2024-10-07T10:03:32.053Z // kotlin.String | DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format.
val endDate : kotlin.String = 2024-10-07T10:03:32.053Z // kotlin.String | DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format.
val bucketSize : kotlin.String = 0 // kotlin.String | Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15.
val productFw : kotlin.String = 0 // kotlin.String | Filter for this product firmware version.
val deviceOsVersion : kotlin.String = string // kotlin.String | Filter for this Device OS version.
val deviceGroup : kotlin.String = string // kotlin.String | Filter for this device group.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugMetricsEventsGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsEventsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsEventsGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **startDate** | **kotlin.String**| DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **endDate** | **kotlin.String**| DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **bucketSize** | **kotlin.String**| Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15. | [optional] |
| **productFw** | **kotlin.String**| Filter for this product firmware version. | [optional] |
| **deviceOsVersion** | **kotlin.String**| Filter for this Device OS version. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceGroup** | **kotlin.String**| Filter for this device group. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugMetricsFunctionsGet"></a>
# **v1ProductsProductIdOrSlugMetricsFunctionsGet**
> kotlin.String v1ProductsProductIdOrSlugMetricsFunctionsGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)

Get cloud function call health metrics

Reports each time a Particle.function() in firmware is called on a device in this product and the outcome.   Each time bucket contains counts of (keys with a value of 0 will be omitted):   - success: the target device successfully received and responded to the function call. - failure: the target device was unable to process the function call, or was unreachable by the cloud. - offline: the function call attempt was skipped because the device was not connected to the cloud at the time the call was made.   Can be filtered to include only function calls for devices with a certain firmware version, Device OS version or in a certain group.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val startDate : kotlin.String = 2024-10-07T10:03:32.064Z // kotlin.String | DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format.
val endDate : kotlin.String = 2024-10-07T10:03:32.064Z // kotlin.String | DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format.
val bucketSize : kotlin.String = 0 // kotlin.String | Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15.
val productFw : kotlin.String = 0 // kotlin.String | Filter for this product firmware version.
val deviceOsVersion : kotlin.String = string // kotlin.String | Filter for this Device OS version.
val deviceGroup : kotlin.String = string // kotlin.String | Filter for this device group.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugMetricsFunctionsGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsFunctionsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsFunctionsGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **startDate** | **kotlin.String**| DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **endDate** | **kotlin.String**| DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **bucketSize** | **kotlin.String**| Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15. | [optional] |
| **productFw** | **kotlin.String**| Filter for this product firmware version. | [optional] |
| **deviceOsVersion** | **kotlin.String**| Filter for this Device OS version. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceGroup** | **kotlin.String**| Filter for this device group. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugMetricsIntegrationGet"></a>
# **v1ProductsProductIdOrSlugMetricsIntegrationGet**
> kotlin.String v1ProductsProductIdOrSlugMetricsIntegrationGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)

Get integration traffic health metrics

Reports outbound integration requests that are sent on behalf of this product fleet and the outcome.   Each time bucket contains counts of (keys with a value of 0 will be omitted):   - success: the integration request was accepted by the destination service (2xx response code). - failure: the integration request was rejected by the destination service (network error, 4xx or 5xx response code) after 3 attempts. - sleep (skipped): the integration request was not attempted because there have been too many previous failures in rapid succession.   Can be filtered to include only integrations triggered by devices with a certain firmware version, Device OS version or in a certain group.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val startDate : kotlin.String = 2024-10-07T10:03:32.054Z // kotlin.String | DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format.
val endDate : kotlin.String = 2024-10-07T10:03:32.054Z // kotlin.String | DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format.
val bucketSize : kotlin.String = 0 // kotlin.String | Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15.
val productFw : kotlin.String = 0 // kotlin.String | Filter for this product firmware version.
val deviceOsVersion : kotlin.String = string // kotlin.String | Filter for this Device OS version.
val deviceGroup : kotlin.String = string // kotlin.String | Filter for this device group.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugMetricsIntegrationGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsIntegrationGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsIntegrationGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **startDate** | **kotlin.String**| DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **endDate** | **kotlin.String**| DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **bucketSize** | **kotlin.String**| Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15. | [optional] |
| **productFw** | **kotlin.String**| Filter for this product firmware version. | [optional] |
| **deviceOsVersion** | **kotlin.String**| Filter for this Device OS version. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceGroup** | **kotlin.String**| Filter for this device group. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugMetricsOnlineGet"></a>
# **v1ProductsProductIdOrSlugMetricsOnlineGet**
> kotlin.String v1ProductsProductIdOrSlugMetricsOnlineGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)

Get online devices metrics

Reports the number of devices online in this product.   Each time bucket contains:   - count: the number of devices with an active cloud connection.   Devices that recently went offline (due to being powered off or losing network connectivity) will be counted as online until the device misses sending a keep-alive message to the cloud. This is typically 25 seconds for Wi-Fi networks and 23 minutes for cellular networks.   Can be filtered to include only devices with a certain firmware version, Device OS version or in a certain group.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val startDate : kotlin.String = 2024-10-07T10:03:32.051Z // kotlin.String | DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format.
val endDate : kotlin.String = 2024-10-07T10:03:32.051Z // kotlin.String | DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format.
val bucketSize : kotlin.String = 0 // kotlin.String | Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15.
val productFw : kotlin.String = 0 // kotlin.String | Filter for this product firmware version.
val deviceOsVersion : kotlin.String = string // kotlin.String | Filter for this Device OS version.
val deviceGroup : kotlin.String = string // kotlin.String | Filter for this device group.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugMetricsOnlineGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsOnlineGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsOnlineGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **startDate** | **kotlin.String**| DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **endDate** | **kotlin.String**| DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **bucketSize** | **kotlin.String**| Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15. | [optional] |
| **productFw** | **kotlin.String**| Filter for this product firmware version. | [optional] |
| **deviceOsVersion** | **kotlin.String**| Filter for this Device OS version. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceGroup** | **kotlin.String**| Filter for this device group. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugMetricsVariablesGet"></a>
# **v1ProductsProductIdOrSlugMetricsVariablesGet**
> kotlin.String v1ProductsProductIdOrSlugMetricsVariablesGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)

Get cloud variable request health metrics

Reports each time a Particle.variable() in firmware is read on a device in this product and the outcome.   Each time bucket contains counts of (keys with a value of 0 will be omitted):   - success: the target device successfully received and responded to the variable request. - failure: the target device was unable to process the variable request, or was unreachable by the cloud. - offline: the variable request was skipped because the device was not connected to the cloud at the time the call was made.   Can be filtered to include only variable requests for devices with a certain firmware version, Device OS version or in a certain group.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug.
val startDate : kotlin.String = 2024-10-07T10:03:32.066Z // kotlin.String | DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format.
val endDate : kotlin.String = 2024-10-07T10:03:32.066Z // kotlin.String | DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format.
val bucketSize : kotlin.String = 0 // kotlin.String | Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15.
val productFw : kotlin.String = 0 // kotlin.String | Filter for this product firmware version.
val deviceOsVersion : kotlin.String = string // kotlin.String | Filter for this Device OS version.
val deviceGroup : kotlin.String = string // kotlin.String | Filter for this device group.
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugMetricsVariablesGet(productIdOrSlug, startDate, endDate, bucketSize, productFw, deviceOsVersion, deviceGroup)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsVariablesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugMetricsVariablesGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. | |
| **startDate** | **kotlin.String**| DateTime to start on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **endDate** | **kotlin.String**| DateTime to end on (inclusive), defaults to the current time. Date in ISO8601 format. | [optional] |
| **bucketSize** | **kotlin.String**| Number of seconds for each bucket, defaults to 900 (15 minutes). Must be greater than 15. | [optional] |
| **productFw** | **kotlin.String**| Filter for this product firmware version. | [optional] |
| **deviceOsVersion** | **kotlin.String**| Filter for this Device OS version. | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **deviceGroup** | **kotlin.String**| Filter for this device group. | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugSimsDataUsageGet"></a>
# **v1ProductsProductIdOrSlugSimsDataUsageGet**
> kotlin.String v1ProductsProductIdOrSlugSimsDataUsageGet(productIdOrSlug)

Get data usage for product fleet

Get fleet-wide SIM card data usage for a product in the current billing period, broken out by day. Daily usage totals represent an aggregate of all SIM cards that make up the product. Data usage reports can be delayed until the next day, and occasionally by several days.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugSimsDataUsageGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugSimsDataUsageGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugSimsDataUsageGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugSimsPost"></a>
# **v1ProductsProductIdOrSlugSimsPost**
> kotlin.String v1ProductsProductIdOrSlugSimsPost(productIdOrSlug, body)

Import and activate product SIMs

Import a group of SIM cards into a product. SIM cards will be activated upon import. Either pass an array of ICCIDs or include a file containing a list of SIM cards.   Import and activation will be queued for processing. You will receive an email with the import results when all SIM cards have been processed.   Importing a SIM card associated with a device will also import the device into the product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
val body : kotlin.String = {
  "sims": [
    "string",
    "string"
  ]
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugSimsPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugSimsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugSimsPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugTeamGet"></a>
# **v1ProductsProductIdOrSlugTeamGet**
> kotlin.String v1ProductsProductIdOrSlugTeamGet(productIdOrSlug)

List team members

List all team members that are part of a given product. Also, will retrieve all API users for all the products inside the org

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugTeamGet(productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugTeamPost"></a>
# **v1ProductsProductIdOrSlugTeamPost**
> kotlin.String v1ProductsProductIdOrSlugTeamPost(productIdOrSlug, body)

Create an API user

Create an API user with specified scopes. This API user will have a single non-expiring access token.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val body : kotlin.String = {
  "friendly_name": "string",
  "scopes": [
    "string",
    "string"
  ]
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugTeamPost(productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamPost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugTeamUsernameDelete"></a>
# **v1ProductsProductIdOrSlugTeamUsernameDelete**
> kotlin.String v1ProductsProductIdOrSlugTeamUsernameDelete(productIdOrSlug, username)

Remove team member

Remove a current team member.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val username : kotlin.String = string // kotlin.String | Username of the team member to be removed
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugTeamUsernameDelete(productIdOrSlug, username)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamUsernameDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamUsernameDelete")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **username** | **kotlin.String**| Username of the team member to be removed | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugTeamUsernamePost"></a>
# **v1ProductsProductIdOrSlugTeamUsernamePost**
> kotlin.String v1ProductsProductIdOrSlugTeamUsernamePost(productIdOrSlug, username, contentType)

Update team member

Update a current team member.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val username : kotlin.String = string // kotlin.String | Username of the team member to be updated
val contentType : kotlin.String = application/json // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugTeamUsernamePost(productIdOrSlug, username, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamUsernamePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamUsernamePost")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| **username** | **kotlin.String**| Username of the team member to be updated | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**|  | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1ProductsProductIdOrSlugTeamUsernameTokenPut"></a>
# **v1ProductsProductIdOrSlugTeamUsernameTokenPut**
> kotlin.String v1ProductsProductIdOrSlugTeamUsernameTokenPut(productIdOrSlug, username, contentType)

Regenerate programmatic user&#39;s token

Regenerate programmatic users token

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or Slug
val username : kotlin.String = string // kotlin.String | Username of the team member to be updated
val contentType : kotlin.String = application/json // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1ProductsProductIdOrSlugTeamUsernameTokenPut(productIdOrSlug, username, contentType)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamUsernameTokenPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1ProductsProductIdOrSlugTeamUsernameTokenPut")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or Slug | |
| **username** | **kotlin.String**| Username of the team member to be updated | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **contentType** | **kotlin.String**|  | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1SerialNumbersSerialNumberGet"></a>
# **v1SerialNumbersSerialNumberGet**
> kotlin.String v1SerialNumbersSerialNumberGet(serialNumber)

Look up device identification from a serial number

Return the device ID and SIM card ICCD (if applicable) for a device by serial number. This API can look up devices that you have not yet added to your product and is rate limited to 50 requests per hour. Once you&#39;ve imported your devices to your product you should instead use the list devices in a product API and filter on serial number. No special rate limits apply to that API.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val serialNumber : kotlin.String = string // kotlin.String | The serial number printed on the barcode of the device packaging.
try {
    val result : kotlin.String = apiInstance.v1SerialNumbersSerialNumberGet(serialNumber)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SerialNumbersSerialNumberGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SerialNumbersSerialNumberGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **serialNumber** | **kotlin.String**| The serial number printed on the barcode of the device packaging. | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1SimsGet"></a>
# **v1SimsGet**
> kotlin.String v1SimsGet(productIdOrSlug, iccid, deviceId, deviceName, page, perPage)

List SIM cards

Get a list of the SIM cards owned by an individual or a product. The product endpoint is paginated, by default returns 25 SIM card records per page.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
val iccid : kotlin.String = string // kotlin.String | Filter results to SIMs with this ICCID (partial matching)
val deviceId : kotlin.String = string // kotlin.String | Filter results to SIMs with this associated device ID (partial matching)
val deviceName : kotlin.String = string // kotlin.String | Filter results to SIMs with this associated device name (partial matching)
val page : kotlin.String = 0 // kotlin.String | Current page of results
val perPage : kotlin.String = 0 // kotlin.String | Records per page
try {
    val result : kotlin.String = apiInstance.v1SimsGet(productIdOrSlug, iccid, deviceId, deviceName, page, perPage)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SimsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SimsGet")
    e.printStackTrace()
}
```

### Parameters
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |
| **iccid** | **kotlin.String**| Filter results to SIMs with this ICCID (partial matching) | [optional] |
| **deviceId** | **kotlin.String**| Filter results to SIMs with this associated device ID (partial matching) | [optional] |
| **deviceName** | **kotlin.String**| Filter results to SIMs with this associated device name (partial matching) | [optional] |
| **page** | **kotlin.String**| Current page of results | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **perPage** | **kotlin.String**| Records per page | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1SimsIccidDataUsageGet"></a>
# **v1SimsIccidDataUsageGet**
> kotlin.String v1SimsIccidDataUsageGet(iccid, productIdOrSlug)

Get data usage

Get SIM card data usage for the current billing period, broken out by day. Note that date usage reports can be delayed by up to 1 hour.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val iccid : kotlin.String = string // kotlin.String | The ICCID of the desired SIM
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
try {
    val result : kotlin.String = apiInstance.v1SimsIccidDataUsageGet(iccid, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SimsIccidDataUsageGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SimsIccidDataUsageGet")
    e.printStackTrace()
}
```

### Parameters
| **iccid** | **kotlin.String**| The ICCID of the desired SIM | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1SimsIccidDelete"></a>
# **v1SimsIccidDelete**
> kotlin.String v1SimsIccidDelete(iccid, productIdOrSlug)

Release SIM from account

Remove a SIM card from an account, disassociating the SIM card from a user or a product. The SIM will also be deactivated.   Once the SIM card has been released, it can be claimed by a different user, or imported into a different product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val iccid : kotlin.String = string // kotlin.String | The ICCID of the desired SIM
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
try {
    val result : kotlin.String = apiInstance.v1SimsIccidDelete(iccid, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SimsIccidDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SimsIccidDelete")
    e.printStackTrace()
}
```

### Parameters
| **iccid** | **kotlin.String**| The ICCID of the desired SIM | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1SimsIccidGet"></a>
# **v1SimsIccidGet**
> kotlin.String v1SimsIccidGet(iccid, productIdOrSlug)

Get SIM information

Retrieve a SIM card owned by an individual or a product.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val iccid : kotlin.String = string // kotlin.String | Filter results to SIMs with this ICCID (partial matching) Product endpoint only
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
try {
    val result : kotlin.String = apiInstance.v1SimsIccidGet(iccid, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SimsIccidGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SimsIccidGet")
    e.printStackTrace()
}
```

### Parameters
| **iccid** | **kotlin.String**| Filter results to SIMs with this ICCID (partial matching) Product endpoint only | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1SimsIccidPut"></a>
# **v1SimsIccidPut**
> kotlin.String v1SimsIccidPut(iccid, productIdOrSlug, body)

Reactivate SIM

Re-enables a SIM card to connect to a cell tower. Do this if you&#39;d like to reactivate a SIM that you have deactivated.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val iccid : kotlin.String = string // kotlin.String | The ICCID of the SIM to update
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
val body : kotlin.String = {
  "action": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1SimsIccidPut(iccid, productIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SimsIccidPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SimsIccidPut")
    e.printStackTrace()
}
```

### Parameters
| **iccid** | **kotlin.String**| The ICCID of the SIM to update | |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1SimsIccidStatusGet"></a>
# **v1SimsIccidStatusGet**
> kotlin.String v1SimsIccidStatusGet(iccid, productIdOrSlug)

Get cellular network status

Get cellular network status for a given device. Kicks off a long running task that checks if the device/SIM has an active data session with a cell tower. Values for keys in the sim_status object will be null until the task has finished. Poll the endpoint until meta.state is complete. At this point, the sim_status object will be populated.   Note that responses are cached by the cellular network providers. This means that on occasion, the real-time status of the device/SIM may not align with the results of this test.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val iccid : kotlin.String = string // kotlin.String | The ICCID of the desired SIM
val productIdOrSlug : kotlin.String = string // kotlin.String | Product ID or slug. Product endpoint only
try {
    val result : kotlin.String = apiInstance.v1SimsIccidStatusGet(iccid, productIdOrSlug)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1SimsIccidStatusGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1SimsIccidStatusGet")
    e.printStackTrace()
}
```

### Parameters
| **iccid** | **kotlin.String**| The ICCID of the desired SIM | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **productIdOrSlug** | **kotlin.String**| Product ID or slug. Product endpoint only | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UserProductsGet"></a>
# **v1UserProductsGet**
> kotlin.String v1UserProductsGet()

List products

List products the currently authenticated user has access to

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.v1UserProductsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UserProductsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UserProductsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UserServiceAgreementsGet"></a>
# **v1UserServiceAgreementsGet**
> kotlin.String v1UserServiceAgreementsGet()

Get user service agreements

Get the service agreements related to a user

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.String = apiInstance.v1UserServiceAgreementsGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UserServiceAgreementsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UserServiceAgreementsGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UserServiceAgreementsServiceAgreementIdNotificationsGet"></a>
# **v1UserServiceAgreementsServiceAgreementIdNotificationsGet**
> kotlin.String v1UserServiceAgreementsServiceAgreementIdNotificationsGet(serviceAgreementId)

Get notifications for current usage period

Get user notifications related to a specific service agreement   - Usage reached a certain threshold (70%, 90%, 100%) - Service was paused - Service was unpaused

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val serviceAgreementId : kotlin.String = string // kotlin.String | Service agreement ID
try {
    val result : kotlin.String = apiInstance.v1UserServiceAgreementsServiceAgreementIdNotificationsGet(serviceAgreementId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UserServiceAgreementsServiceAgreementIdNotificationsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UserServiceAgreementsServiceAgreementIdNotificationsGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **serviceAgreementId** | **kotlin.String**| Service agreement ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UserServiceAgreementsServiceAgreementIdUsageReportsPost"></a>
# **v1UserServiceAgreementsServiceAgreementIdUsageReportsPost**
> kotlin.String v1UserServiceAgreementsServiceAgreementIdUsageReportsPost(serviceAgreementId, body)

Create a user usage report

Request a new usage report related to the user service agreement.   The usage report will be processed asynchronously. Expect its \&quot;state\&quot; to change throughout time.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val serviceAgreementId : kotlin.String =  // kotlin.String | Service Agreement ID
val body : kotlin.String = {
  "report_type": "string",
  "date_period_start": "string",
  "date_period_end": "string",
  "devices": [
    "string",
    "string"
  ],
  "products": [
    "string",
    "string"
  ]
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UserServiceAgreementsServiceAgreementIdUsageReportsPost(serviceAgreementId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UserServiceAgreementsServiceAgreementIdUsageReportsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UserServiceAgreementsServiceAgreementIdUsageReportsPost")
    e.printStackTrace()
}
```

### Parameters
| **serviceAgreementId** | **kotlin.String**| Service Agreement ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1UserUsageReportsUsageReportIdGet"></a>
# **v1UserUsageReportsUsageReportIdGet**
> kotlin.String v1UserUsageReportsUsageReportIdGet(usageReportId)

Get a user usage report

Get a single usage report related to the user. Expect \&quot;download_url\&quot; to be present only when the usage report has an \&quot;available\&quot; state.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val usageReportId : kotlin.String =  // kotlin.String | The usage report ID
try {
    val result : kotlin.String = apiInstance.v1UserUsageReportsUsageReportIdGet(usageReportId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UserUsageReportsUsageReportIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UserUsageReportsUsageReportIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **usageReportId** | **kotlin.String**| The usage report ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersGet"></a>
# **v1UsersLedgersGet**
> kotlin.String v1UsersLedgersGet(orgIdOrSlug, page, perPage, archived)

List ledger definitions

Lists all ledger definitions belonging to the Sandbox or organization.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val page : kotlin.String = 0 // kotlin.String | Page number
val perPage : kotlin.String = 0 // kotlin.String | Number of definitions per page
val archived : kotlin.String = false // kotlin.String | Filter for archived definitions
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersGet(orgIdOrSlug, page, perPage, archived)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **page** | **kotlin.String**| Page number | [optional] |
| **perPage** | **kotlin.String**| Number of definitions per page | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **archived** | **kotlin.String**| Filter for archived definitions | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameDelete"></a>
# **v1UsersLedgersLedgerNameDelete**
> kotlin.String v1UsersLedgersLedgerNameDelete(orgIdOrSlug, ledgerName)

Archive a ledger definition

Archives a ledger definition.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameDelete(orgIdOrSlug, ledgerName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameDelete")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **ledgerName** | **kotlin.String**| Ledger name | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameGet"></a>
# **v1UsersLedgersLedgerNameGet**
> kotlin.String v1UsersLedgersLedgerNameGet(orgIdOrSlug, ledgerName)

Get ledger definition

Returns the specified ledger definition.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameGet(orgIdOrSlug, ledgerName)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **ledgerName** | **kotlin.String**| Ledger name | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameInstancesGet"></a>
# **v1UsersLedgersLedgerNameInstancesGet**
> kotlin.String v1UsersLedgersLedgerNameInstancesGet(orgIdOrSlug, ledgerName, page, perPage)

List ledger instances

Lists all ledger instances.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val page : kotlin.String = 0 // kotlin.String | Page number
val perPage : kotlin.String = 0 // kotlin.String | Number of instances per page
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameInstancesGet(orgIdOrSlug, ledgerName, page, perPage)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| **page** | **kotlin.String**| Page number | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **perPage** | **kotlin.String**| Number of instances per page | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameInstancesScopeValueDelete"></a>
# **v1UsersLedgersLedgerNameInstancesScopeValueDelete**
> kotlin.String v1UsersLedgersLedgerNameInstancesScopeValueDelete(orgIdOrSlug, ledgerName, scopeValue)

Delete ledger instance

Deletes the specified ledger instance.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val scopeValue : kotlin.String = string // kotlin.String | Scope value
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameInstancesScopeValueDelete(orgIdOrSlug, ledgerName, scopeValue)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueDelete")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **scopeValue** | **kotlin.String**| Scope value | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameInstancesScopeValueGet"></a>
# **v1UsersLedgersLedgerNameInstancesScopeValueGet**
> kotlin.String v1UsersLedgersLedgerNameInstancesScopeValueGet(orgIdOrSlug, ledgerName, scopeValue)

Get ledger instance

Returns the specified ledger instance.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val scopeValue : kotlin.String = string // kotlin.String | Scope value
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameInstancesScopeValueGet(orgIdOrSlug, ledgerName, scopeValue)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **scopeValue** | **kotlin.String**| Scope value | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameInstancesScopeValuePut"></a>
# **v1UsersLedgersLedgerNameInstancesScopeValuePut**
> kotlin.String v1UsersLedgersLedgerNameInstancesScopeValuePut(orgIdOrSlug, ledgerName, scopeValue, body)

Set the ledger instance data

Sets the data of the specified ledger instance.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val scopeValue : kotlin.String = string // kotlin.String | Scope value
val body : kotlin.String = {
  "data": {
    "key": "value"
  }
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameInstancesScopeValuePut(orgIdOrSlug, ledgerName, scopeValue, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValuePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValuePut")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| **scopeValue** | **kotlin.String**| Scope value | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet"></a>
# **v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet**
> kotlin.String v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet(orgIdOrSlug, ledgerName, scopeValue, replacedBefore, replacedAfter)

List ledger instance versions

Lists all ledger instance versions.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val scopeValue : kotlin.String = string // kotlin.String | Scope value
val replacedBefore : kotlin.String = string // kotlin.String | List only versions replaced before this date
val replacedAfter : kotlin.String = string // kotlin.String | List only versions replaced after this date
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet(orgIdOrSlug, ledgerName, scopeValue, replacedBefore, replacedAfter)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueVersionsGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| **scopeValue** | **kotlin.String**| Scope value | |
| **replacedBefore** | **kotlin.String**| List only versions replaced before this date | [optional] |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **replacedAfter** | **kotlin.String**| List only versions replaced after this date | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet"></a>
# **v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet**
> kotlin.String v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet(orgIdOrSlug, ledgerName, scopeValue, version)

Get ledger instance version

Returns the specified version of a ledger instance.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val scopeValue : kotlin.String = string // kotlin.String | Scope value
val version : kotlin.String = string // kotlin.String | Version ID
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet(orgIdOrSlug, ledgerName, scopeValue, version)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNameInstancesScopeValueVersionsVersionGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| **scopeValue** | **kotlin.String**| Scope value | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **version** | **kotlin.String**| Version ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLedgersLedgerNamePut"></a>
# **v1UsersLedgersLedgerNamePut**
> kotlin.String v1UsersLedgersLedgerNamePut(orgIdOrSlug, ledgerName, body)

Update ledger definition

Updates the ledger definition.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val ledgerName : kotlin.String = string // kotlin.String | Ledger name
val body : kotlin.String = {
  "definition": {
    "key": "value"
  }
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersLedgerNamePut(orgIdOrSlug, ledgerName, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersLedgerNamePut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersLedgerNamePut")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **ledgerName** | **kotlin.String**| Ledger name | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1UsersLedgersPost"></a>
# **v1UsersLedgersPost**
> kotlin.String v1UsersLedgersPost(orgIdOrSlug, body)

Create a new ledger definition

Creates a ledger definition.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val body : kotlin.String = {
  "definition": {
    "key": "value"
  }
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UsersLedgersPost(orgIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLedgersPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLedgersPost")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1UsersLogicExecutePost"></a>
# **v1UsersLogicExecutePost**
> kotlin.String v1UsersLogicExecutePost(orgIdOrSlug, body)

Execute logic function

Executes the provided logic function.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val body : kotlin.String = {
  "source": {
    "key": "value",
    "type": "string",
    "code": "string"
  },
  "event": {
    "key": "value",
    "event_data": "string",
    "device_id": "string",
    "product_id": "string"
  },
  "api_username": "string"
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UsersLogicExecutePost(orgIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicExecutePost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicExecutePost")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsGet"></a>
# **v1UsersLogicFunctionsGet**
> kotlin.String v1UsersLogicFunctionsGet(orgIdOrSlug, todayStats)

List logic functions

Lists all logic functions belonging to the specified Sandbox or organization.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val todayStats : kotlin.String = false // kotlin.String | Include today's stats
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsGet(orgIdOrSlug, todayStats)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **todayStats** | **kotlin.String**| Include today&#39;s stats | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdDelete"></a>
# **v1UsersLogicFunctionsLogicFunctionIdDelete**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdDelete(orgIdOrSlug, logicFunctionId)

Delete logic function

Deletes the specified logic function.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdDelete(orgIdOrSlug, logicFunctionId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdDelete")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdGet"></a>
# **v1UsersLogicFunctionsLogicFunctionIdGet**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdGet(orgIdOrSlug, logicFunctionId)

Get logic function

Returns the specified logic function.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdGet(orgIdOrSlug, logicFunctionId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdPut"></a>
# **v1UsersLogicFunctionsLogicFunctionIdPut**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdPut(orgIdOrSlug, logicFunctionId, body)

Update logic function

Updates the logic function.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
val body : kotlin.String = {
  "logic_function": {
    "key": "value"
  }
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdPut(orgIdOrSlug, logicFunctionId, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdPut")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdRunsGet"></a>
# **v1UsersLogicFunctionsLogicFunctionIdRunsGet**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdRunsGet(orgIdOrSlug, logicFunctionId)

List logic functions runs

Lists all runs for the specified logic function.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdRunsGet(orgIdOrSlug, logicFunctionId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdRunsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdRunsGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet"></a>
# **v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet(orgIdOrSlug, logicFunctionId, logicRunId)

Get logic function run

Returns the specified logic function run.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
val logicRunId : kotlin.String = string // kotlin.String | Logic run ID
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet(orgIdOrSlug, logicFunctionId, logicRunId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logicRunId** | **kotlin.String**| Logic run ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet"></a>
# **v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet(orgIdOrSlug, logicFunctionId, logicRunId)

Get logic function run logs

Returns the specified logic function run logs.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
val logicRunId : kotlin.String = string // kotlin.String | Logic run ID
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet(orgIdOrSlug, logicFunctionId, logicRunId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdRunsLogicRunIdLogsGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logicRunId** | **kotlin.String**| Logic run ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsLogicFunctionIdStatsGet"></a>
# **v1UsersLogicFunctionsLogicFunctionIdStatsGet**
> kotlin.String v1UsersLogicFunctionsLogicFunctionIdStatsGet(orgIdOrSlug, logicFunctionId)

Get logic function stats

Returns the specified logic function stats.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val logicFunctionId : kotlin.String = string // kotlin.String | Logic function ID
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsLogicFunctionIdStatsGet(orgIdOrSlug, logicFunctionId)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdStatsGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsLogicFunctionIdStatsGet")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **logicFunctionId** | **kotlin.String**| Logic function ID | |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="v1UsersLogicFunctionsPost"></a>
# **v1UsersLogicFunctionsPost**
> kotlin.String v1UsersLogicFunctionsPost(orgIdOrSlug, body)

Create a new logic function

Creates a logic function.

### Example
```kotlin
// Import classes:
//import com.ndipatri.kmp.openapi.particle.infrastructure.*
//import com.ndipatri.kmp.openapi.particle.models.*

val apiInstance = DefaultApi()
val orgIdOrSlug : kotlin.String = string // kotlin.String | Organization ID or Slug. Organization endpoint only.
val body : kotlin.String = {
  "logic_function": {
    "key": "value"
  }
} // kotlin.String | 
try {
    val result : kotlin.String = apiInstance.v1UsersLogicFunctionsPost(orgIdOrSlug, body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#v1UsersLogicFunctionsPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#v1UsersLogicFunctionsPost")
    e.printStackTrace()
}
```

### Parameters
| **orgIdOrSlug** | **kotlin.String**| Organization ID or Slug. Organization endpoint only. | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **body** | **kotlin.String**|  | [optional] |

### Return type

**kotlin.String**

### Authorization


Configure bearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: text/plain
 - **Accept**: application/json

