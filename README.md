<img src="https://avatars1.githubusercontent.com/u/29229038" />

REMME Java Client
==========

**An open source Java integration library for REMChain, simplifying the access and interaction with REMME nodes both public or permissioned.**

## How to use
### 1. Install and run REMME node with required RPC API modules enabled.
You can check out how to do that at [REMME core repo](https://github.com/Remmeauth/remme-core/).

### 2. Add dependency to the project
#### Using Maven
Add Maven dependency below to the *pom.xml* file

**coming soon**

#### Manually
a) Checkout (or download) master branch of the repository

b) Run build command
```bash
mvn clean install -DskipTests=true
```
c) Add created *.jar* file to your classpath

d) Do not forget to add required dependencies (list of dependencies you can find in the project *pom.xml* file)
  
### 3. Run methods of **RemmeClient** class to interact with REMME node.

### 4. Possible errors and solutions


## Examples
#### Implement RemmeClient
```java
String privateKeyHex = "7f752a99bbaf6755dc861bb4a7bb19acb913948d75f3b718ff4545d01d9d4f10";
NetworkConfig networkConfig = new NetworkConfig("localhost:8080", false);
RemmeClient remme = new RemmeClient(ClientInit.builder()
        .privateKeyHex(privateKeyHex) 
        .networkConfig(networkConfig).build());
```

#### Tokens
```java
String someAccountPublicKeyInHexFormat = "0306796698d9b14a0ba313acc7fb14f69d8717393af5b02cc292d72009b97d8759";
String someRemmeAddress = Functions.generateAddress(RemmeFamilyName.ACCOUNT, someAccountPublicKeyInHexFormat);
Long balance = remme.getToken().getBalance(someRemmeAddress).get();
System.out.println("Account " + someRemmeAddress + " balance - " + balance + " REM");

BaseTransaction transactionResult = remme.getToken().transfer(someRemmeAddress, 100L);
System.out.println("Sending tokens...BatchId: " + transactionResult.getBatchId());

SocketEventListener transactionCallback = (err, result) -> {
    if (err != null) {return;};
    System.out.println("token " + new ObjectMapper().writeValueAsString(result));
    Long newBalance = await remme.getToken().getBalance(someRemmeAddress).get();
    System.out.println("Account " + someRemmeAddress + "balance - " + newBalance + " REM");
    transactionResult.closeWebSocket();
};

transactionResult.connectToWebSocket(transactionCallback);
```

#### Certificates
```java
ICertificateTransactionResponse certificateTransactionResult = remme.getCertificate().createAndStore(
    CreateCertificateDTO.builder()    
    .commonName("userName1")
    .email("user@email.com")
    .name("John")
    .surname("Smith")
    .countryName("US")
    .validity(360)
    .serial("some serial").build());

SocketEventListener certificateTransactionCallback = (err, response) -> {
    if (err != null) return;
    System.out.println("certificate" + new ObjectMapper().writeValueAsString(response));
    System.out.println("Certificate was saved on REMchain at block number: " + response.getId());
    Boolean certificateStatus = remme.getCertificate().check(certificateTransactionResult.getCertificate()).get();
    System.out.println("Certificate IsValid = " + certificateStatus);
    certificateTransactionResult.closeWebSocket();
};

certificateTransactionResult.connectToWebSocket(certificateTransactionCallback);
```

#### Subscribing to Events
RemmeEvents is enums which describe all available events.
```java
remme.getEvents().subscribe(RemmeRequestParams.builder()
    .events(RemmeEvents.ATOMIC_SWAP).build(), (err, res) -> {
    if (err != null) {
        System.out.println(new ObjectMapper().writeValueAsString(err));
        return;
    }
    System.out.println(new ObjectMapper().writeValueAsString(res));
})
```

Also we give a possibility to start listen events from previous block by providing last known block id

```java
remme.events.subscribe(RemmeRequestParams.builder()
    .events(RemmeEvents.ATOMIC_SWAP)
    .lastKnownBlockId("db19f0e3b3f001670bebc814e238df48cef059f3f0668f57702ba9ff0c4b8ec45c7298f08b4c2fa67602da27a84b3df5dc78ce0f7774b3d3ae094caeeb9cbc82")
    .build(), (err, res) -> {
    if (err != null) {
        System.out.println(new ObjectMapper().writeValueAsString(err));
        return;
    }
    System.out.println(new ObjectMapper().writeValueAsString(res));
});
```

Unsubscribe from listening events

```java
remme.getEvents().unsubscribe();
```

## License

REMME software and documentation are licensed under `Apache License Version 2.0 <LICENSE>`_.