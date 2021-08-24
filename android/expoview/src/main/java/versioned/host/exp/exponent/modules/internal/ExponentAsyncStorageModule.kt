// Copyright 2015-present 650 Industries. All rights reserved.
package versioned.host.exp.exponent.modules.internal

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.modules.storage.AsyncStorageModule
import com.facebook.react.modules.storage.ReactDatabaseSupplier
import expo.modules.updates.manifest.raw.RawManifest
import host.exp.exponent.kernel.ExperienceKey
import host.exp.exponent.kernel.KernelProvider
import org.json.JSONException
import java.io.UnsupportedEncodingException

@ReactModule(name = AsyncStorageModule.NAME, canOverrideExistingModule = true)
class ExponentAsyncStorageModule(reactContext: ReactApplicationContext?, manifest: RawManifest?) :
  AsyncStorageModule(reactContext) {
  override fun canOverrideExistingModule(): Boolean {
    return true
  }

  companion object {
    @Throws(UnsupportedEncodingException::class)
    private fun experienceKeyToDatabaseName(experienceKey: ExperienceKey): String {
      return "RKStorage-scoped-experience-" + experienceKey.getUrlEncodedScopeKey()
    }
  }

  init {
    try {
      val experienceKey = ExperienceKey.fromRawManifest(manifest!!)
      val databaseName = experienceKeyToDatabaseName(experienceKey)
      mReactDatabaseSupplier = ReactDatabaseSupplier(reactContext, databaseName)
    } catch (e: JSONException) {
      KernelProvider.instance.handleError("Requires Experience Id")
    } catch (e: UnsupportedEncodingException) {
      KernelProvider.instance.handleError("Couldn't URL encode Experience Id")
    }
  }
}
