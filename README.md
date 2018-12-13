# EditText
&lt;Android> EditText

## Use

1. In your xml 

```xml
<com.busycount.edittext.SecretEditText
    android:id="@+id/secretEditText"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

also you can add attrs

```xml
<com.busycount.edittext.SecretEditText
    android:id="@+id/secretEditText"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:lineColor="@color/colorPrimary"
    app:lineColorSelect="@color/colorPrimaryDark"
    app:lineWidth="1dp"
    app:pwdSize="4"
    app:textColor="@color/colorPrimaryDark" />
```


2. In your Activity

```java
if(secretedittext.isDone()){
  secretEditText.add(c);
}
//delete recent input
secretEditText.delete();
//clear all input
secretedittext.clear();
//get the input text
String text=secretEditText.getString();
```

  

## Implementation
Step 1. Add the JitPack repository to your build file Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.busycount:edittext:0.2'
}
```
