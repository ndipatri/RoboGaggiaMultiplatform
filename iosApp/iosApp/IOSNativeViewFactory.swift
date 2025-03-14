import SwiftUI
import ComposeApp
import RiveRuntime

class IOSNativeViewFactory: NativeViewFactory {
    static var shared = IOSNativeViewFactory()
    
    func renderWelcomeAnimation() -> UIViewController {
        let riveView = RiveViewModel(fileName: "nick").view()
        
        // Ensure the SwiftUI view has a transparent background
        let fullScreenView = VStack {
            Spacer()
            riveView
                .frame(maxWidth: .infinity, maxHeight: .infinity)
            Spacer()
        }
        .background(Color.black) // ✅ Make SwiftUI background transparent

        let hostingController = UIHostingController(rootView: fullScreenView)
        hostingController.view.backgroundColor = .black // ✅ Ensure UIKit background is also transparent
        
        return hostingController
    }
}






