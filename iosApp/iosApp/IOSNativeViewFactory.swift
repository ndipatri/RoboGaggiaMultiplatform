import SwiftUI
import ComposeApp
import RiveRuntime

class IOSNativeViewFactory: NativeViewFactory {
    static var shared = IOSNativeViewFactory()
    func renderIntroAnimation() -> UIViewController {

       var viewModel = RiveViewModel(webURL: "https://cdn.rive.app/animations/truck.riv" )
       let view = viewModel.view()
        
       return UIHostingController(rootView: view)
   }
}
