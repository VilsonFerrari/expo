package versioned.host.exp.exponent.modules.universal.av

import android.content.Context
import com.facebook.react.bridge.ReactContext
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.TransferListener
import expo.modules.av.player.datasource.SharedCookiesDataSourceFactoryProvider
import expo.modules.core.ModuleRegistry
import host.exp.exponent.utils.ScopedContext

class SharedCookiesDataSourceFactoryProvider : SharedCookiesDataSourceFactoryProvider() {
  override fun createFactory(
    context: Context,
    moduleRegistry: ModuleRegistry,
    userAgent: String,
    requestHeaders: Map<String, Any>?,
    transferListener: TransferListener
  ): DataSource.Factory {
    var reactContext: ReactContext? = null
    if (context is ReactContext) {
      reactContext = context
    } else if (context is ScopedContext) {
      reactContext = context.context as ReactContext
    }
    return SharedCookiesDataSourceFactory(reactContext, userAgent, requestHeaders, transferListener)
  }
}
