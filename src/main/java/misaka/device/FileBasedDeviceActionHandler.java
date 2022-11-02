package misaka.device;

import artoria.action.AbstractActionHandler;
import artoria.action.handler.InfoHandler;
import artoria.beans.BeanUtils;
import artoria.file.Csv;
import artoria.util.Assert;
import artoria.util.ObjectUtils;
import artoria.util.RecombineUtils;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FileBasedDeviceActionHandler extends AbstractActionHandler implements InfoHandler {
    private Map<String, Device> deviceMap;

    public FileBasedDeviceActionHandler(Csv csv) {
        List<Device> deviceList = BeanUtils.mapToBeanInList(csv.toMapList(), Device.class);
        Map<String, Device> modelMap = RecombineUtils.listToMapBean(deviceList, "model");
        deviceMap = Collections.unmodifiableMap(modelMap);
    }

    @Override
    public <T> T info(Object input, Class<T> clazz) {
        isSupport(new Class[]{ Device.class }, clazz);
        DeviceQuery deviceQuery = (DeviceQuery) input;
        String model = deviceQuery.getModel();
        Assert.notBlank(model, "Parameter \"model\" must not blank. ");
        return ObjectUtils.cast(deviceMap.get(model));
    }

    @Override
    public <T> T execute(Object input, Type type) {

        return info(input, (Class<T>) type);
    }

}
