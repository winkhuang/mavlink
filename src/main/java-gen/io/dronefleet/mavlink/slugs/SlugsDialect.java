package io.dronefleet.mavlink.slugs;

import io.dronefleet.mavlink.MavlinkDialect;
import io.dronefleet.mavlink.MavlinkDialects;
import java.lang.Class;
import java.lang.IllegalArgumentException;
import java.lang.Override;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SlugsDialect implements MavlinkDialect {
    private static final Set<MavlinkDialect> dependencies;

    static {
        dependencies = Stream.of(
                MavlinkDialects.COMMON)
                .collect(Collectors.toSet());
    }

    @Override
    public final boolean supports(int messageId) {
        switch (messageId) {
            case 170:
            case 172:
            case 173:
            case 176:
            case 177:
            case 179:
            case 180:
            case 181:
            case 184:
            case 185:
            case 186:
            case 188:
            case 189:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            return true;
        }
        return dependencies.stream()
                .anyMatch(d -> d.supports(messageId));
    }

    @Override
    public final Class resolve(int messageId) {
        switch (messageId) {
            case 170: return CpuLoad.class;
            case 172: return SensorBias.class;
            case 173: return Diagnostic.class;
            case 176: return SlugsNavigation.class;
            case 177: return DataLog.class;
            case 179: return GpsDateTime.class;
            case 180: return MidLvlCmds.class;
            case 181: return CtrlSrfcPt.class;
            case 184: return SlugsCameraOrder.class;
            case 185: return ControlSurface.class;
            case 186: return SlugsMobileLocation.class;
            case 188: return SlugsConfigurationCamera.class;
            case 189: return IsrLocation.class;
            case 191: return VoltSensor.class;
            case 192: return PtzStatus.class;
            case 193: return UavStatus.class;
            case 194: return StatusGps.class;
            case 195: return NovatelDiag.class;
            case 196: return SensorDiag.class;
            case 197: return Boot.class;
        }
        return dependencies.stream()
                .filter(d -> d.supports(messageId))
                .map(d -> d.resolve(messageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Dialect slugs does not support a message of ID " + messageId));
    }
}