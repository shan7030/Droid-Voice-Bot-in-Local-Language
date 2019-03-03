<p align="center">
  <img src="https://user-images.githubusercontent.com/12429051/32413550-684f8b46-c239-11e7-89d5-b4ab4905b369.png" width="200"/>
</p>

<b><h1>Usage</h1></b>
<b>Gradle dependency:</b>

Add the following to your project level build.gradle:

```java
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Add this to your app build.gradle:

```java
dependencies {
    compile 'com.github.vikramezhil:DroidSpeech:v2.0.3’
}
```

<b>Maven:</b>

Add the following to the <repositories> section of your pom.xml:

```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Add the following to the <dependencies> section of your pom.xml:

```xml
<dependency>
    <groupId>com.github.vikramezhil</groupId>
    <artifactId>DroidSpeech</artifactId>
    <version>v2.0.3</version>
</dependency>
```

<b><h1>Documentation</h1></b>

For a detailed documentation 📔, please have a look at the [Wiki](https://github.com/vikramezhil/DroidSpeech/wiki).

In your activity, initialize the droid speech class and set the droid speech listener

```java
DroidSpeech droidSpeech = new DroidSpeech(this, null);
droidSpeech.setOnDroidSpeechListener(this);
```
To start droid speech to listen to user voice call the method `startDroidSpeechRecognition()`,

```java
droidSpeech.startDroidSpeechRecognition();
```
To close droid speech operations call the method `closeDroidSpeechOperations()`,

```java
droidSpeech.closeDroidSpeechOperations();
```

The speech result will be triggered at `onDroidSpeechFinalResult`,

```java
@Override
public void onDroidSpeechFinalResult(String finalSpeechResult)
{
  // Do whatever you want with the speech result
}
```
