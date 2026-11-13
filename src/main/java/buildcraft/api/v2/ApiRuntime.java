package buildcraft.api.v2;

import buildcraft.api.v2.registry.ApiRegistry;
import buildcraft.api.v2.registry.RegistryKey;
import buildcraft.api.v2.service.ServiceKey;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/** Immutable API runtime facade. */
public interface ApiRuntime {
    ApiRuntime EMPTY = new ApiRuntime() {
        public <T> Optional<ApiRegistry<T>> registry(Identifier id) { return Optional.empty(); }
        public <T> Optional<T> service(ServiceKey<T> key) { return Optional.empty(); }
        public ApiVersion version() { return new ApiVersion(0, 0, 0); }
        public ApiFeatureSet features() { return ApiFeatureSet.EMPTY; }
        public ApiLifecycle lifecycle() { return ApiLifecycle.DISCOVERY; }
    };

    static ApiRuntime empty() { return EMPTY; }

    <T> Optional<ApiRegistry<T>> registry(Identifier id);
    <T> Optional<T> service(ServiceKey<T> key);

    default <T> Optional<ApiRegistry<T>> registry(RegistryKey<T> key) {
        return registry(key.id());
    }

    default <T> ApiRegistry<T> requireRegistry(RegistryKey<T> key) {
        return registry(key).orElseThrow(() -> new IllegalStateException("BuildCraft API registry is unavailable: " + key.id()));
    }

    default <T> T requireService(ServiceKey<T> key) {
        return service(key).orElseThrow(() -> new IllegalStateException("BuildCraft API service is unavailable: " + key.id()));
    }

    default ApiVersion version() { return BuildCraftApi.VERSION; }
    default ApiFeatureSet features() { return ApiFeatureSet.EMPTY; }
    default ApiLifecycle lifecycle() { return ApiLifecycle.DISCOVERY; }
}
