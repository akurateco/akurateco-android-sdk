![](https://jitpack.io/v/akurateco/akurateco-android-sdk.svg) | [View SDK Wiki](https://github.com/akurateco/akurateco-android-sdk/wiki) | [Report new issue](https://github.com/akurateco/akurateco-android-sdk/issues/new)

# Akurateco Android SDK

Akurateco is a white-label payment software provider. Thanks to our 15+ years of experience in the payment industry, we’ve developed a state-of-the-art white-label payment system that ensures smooth and uninterrupted payment flow for merchants across industries.

<p align="center">
  <a href="https://akurateco.com">
      <img src="/media/header.jpg" alt="Akurateco" width="400px"/>
  </a>
</p>

Akurateco Android SDK was developed and designed with one purpose: to help the Android developers easily integrate the Akurateco API Payment Platform for a specific merchant. 

The main aspects of the Akurateco Android SDK:

- [Kotlin](https://developer.android.com/kotlin) is the main language
- [Retrofit](http://square.github.io/retrofit/) is the API machine 
- [KDoc](https://kotlinlang.org/docs/reference/kotlin-doc.html) code coverage
- API debug [logging](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
- Minimum SDK 16+
- Sample Application

To properly set up the SDK, read [Wiki](https://github.com/akurateco/akurateco-android-sdk/wiki) first.
To get used to the SDK, download a [sample app](https://github.com/akurateco/akurateco-android-sdk/tree/main/sample).

## Setup

Add to the root build.gradle:

```groovy
allprojects {
    repositories {
        ...
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

Add to the package build.gradle:

```groovy
dependencies {
    implementation 'com.github.akurateco:akurateco-android-sdk:{latest-version}'
}
```

Latest version is: ![](https://jitpack.io/v/akurateco/akurateco-android-sdk.svg) 

Also, it is possible to download the latest artifact from the [releases page](https://github.com/akurateco/akurateco-android-sdk/releases).

## Sample

| Sale | Recurring Sale | Capture |
|-|-|-|
| ![](/media/sale.gif) | ![](/media/recurring-sale.gif) | ![](/media/capture.gif) |

| Creditvoid | Get Trans Status | Get Trans Details |
|-|-|-|
| ![](/media/creditvoid.gif) | ![](/media/get-trans-status.gif) | ![](/media/get-trans-details.gif) |

## Getting help

To report a specific issue or feature request, open a [new issue](https://github.com/akurateco/akurateco-android-sdk/issues/new).

Or write a direct letter to the [admin@akurateco.com](mailto:admin@akurateco.com).

## License

MIT License. See the [LICENSE](https://github.com/akurateco/akurateco-android-sdk/blob/main/LICENSE) file for more details.

## Contacts

![](/media/footer.jpg)

Website: https://akurateco.com  
Phone: [+31-638-7642-70](tel:31638764270)  
Email: [admin@akurateco.com](mailto:admin@akurateco.com)  
Address: Akurateco BV, Kingsfordweg 151, 1043 GR Amsterdam, The Netherlands  

© 2014 - 2020 Akurateco. All rights reserved.
