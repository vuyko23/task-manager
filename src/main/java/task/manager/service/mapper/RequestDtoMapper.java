package task.manager.service.mapper;

public interface RequestDtoMapper<D, T> {
    D mapToModel(T t);
}
