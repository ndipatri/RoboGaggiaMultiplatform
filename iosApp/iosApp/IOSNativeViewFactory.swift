import SwiftUI
import ComposeApp
import RiveRuntime

class IOSNativeViewFactory: NativeViewFactory {
    static var shared = IOSNativeViewFactory()
    
    func renderWelcomeAnimation() -> UIViewController {
       let view = RiveViewModel(fileName: "nick").view()
        
       return UIHostingController(rootView: view)
   }
}
