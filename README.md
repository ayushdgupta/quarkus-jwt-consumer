Here we will create few API's that will allow certain users to access that API based on their roles.

commands to generate keys (in Git bash)
1--> First of all you have to create a private key in PEM format.
openssl genrsa -out ./private.key 4096		 here instead of 4096 we can use 2048 as well
openssl genrsa -out ./rsaprivateKey.pem 2048

2--> Now, you have to create one public key from the already generated private key. Use the same tool used in the step before.
openssl rsa -in rsaprivateKey.pem -pubout -outform PEM -out publicKey.pem

3--> to convert the format of privatekey
openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaprivateKey.pem -outform pem -out privateKey.pem

4-->
chmod 600 rsaprivateKey.pem
chmod 600 privateKey.pem

// below commands given on quarkus website
1--> openssl genrsa -out rsaPrivateKey.pem 2048

2--> openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem

3--> An additional step is needed for generating the private key for converting it into the PKCS#8 format.
openssl command for converting private key
openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pem
