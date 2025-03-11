package content

// Props to Phillip Lackner for his idea of using a 'bridging'
// interface to access Swift.

// Here's my explanation of all this:
//
// In order to get a Composable in a Compose Multiplatform project to render  a Rive Animation
// in either native Android or iOS, you need to use the expect/actual mechanism of KMP.
//
// Obviously the ‘actual’ Android implementation is easy as the Android Rive Runtime supports
// Composables directly, right? Well, no..  Rive is view-based, so you have to wrap it in an
// AndroidView Composable.
//
// The harder challenge is how to wrap a native iOS Rive Animation in a Composable. Recall that
// the ‘actual’ iOS implementation has to be done inside of ‘iosMain’.  iosMain is the
// ‘Kotlin-based iOS specific’ part of any KMP project. iosMain cannot talk directly to Swift code;
// it can only talk to Object-C source code. You CAN try supporting Swift code if you have access
// to the source code and can annotate it with ‘objc’ annotations AND also utilize the ‘cinterop’
// tools.  This will create ‘Object-C Wrapper or Binding classes’ for Swift code. In this way,
// you can potentially call Swift code from inside of iosMain.  I tried this but it was not worth
// the trouble as there is an easier approach as you will see.
//
// So then I tried using UIKitView, which is part of the Compose Multiplatform ‘view interop’
// library.. It converts a UIKit UIView, which is a predecessor to SwiftUI, to a Composable.
// This is potentially useful as UIView is based on NSObject and is all accessible with Objective-C.
// It appeared as though the Rive Runtime classes WERE built with ‘objc’ bindings, but I could
// NOT get the Rive ‘UIKit’ code to work properly inside of iosMain!!
//
// Ultimately, it seemed the best approach was to do ALL the RiveAnimation work in SwiftUI in the
// native iOS application and then just access that from within iosMain (kotlin-based ios code)
// by using the other ‘view interop’ class call UIKitViewController.
// This takes a UIKit-based UIViewController and turns it into a Composable instead.
//
// This is useful because there is a Swift class called UIHostingController that will convert a
// SwiftUI element to a UIKit UIViewController.
//
// The problem is UIHostingController is a Swift class and it’s not built with ‘objc’ annotations
// so you can’t easily access it from iosMain.  Remember, only Objective-C code can
// be accessed from iosMain.
//
// So you need to build an interface to ‘bridge’ the call. So on the iosMain side,
// you are just calling an Objective-C function that returns a UIViewController, which is also
// Objective-C. This class IS accessible in iosMain as it has obj-c bindings when it is compiled:
//
// @kotlinx.cinterop.ExternalObjCClass public open expect class UIViewController
//
// The critical thing here is that from your iOS project, you CAN access Object-C code that you’ve
// written in iosMain. This ‘bridge’ interface, NativeViewFactory in the example below, is this code.
//
// The actual implementation is done in the main iosApp where Swift is allowed

import platform.UIKit.UIViewController

interface NativeViewFactory {
    fun renderIntroAnimation(): UIViewController
}