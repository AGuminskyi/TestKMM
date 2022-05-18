import SwiftUI
import shared

@main
struct iOSApp: App {
	 let sdk = Sdk(databaseDriverFactory: DatabaseDriverFactory())
        var body: some Scene {
            WindowGroup {
                ContentView(viewModel: .init(sdk: sdk))
            }
        }
}
