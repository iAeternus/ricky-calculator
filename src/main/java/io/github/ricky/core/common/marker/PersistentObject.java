package io.github.ricky.core.common.marker;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/10/11
 * @className PersistentObject
 * @desc 持久层对象，用于描述mysql中的数据
 * <p>
 * 约定<br>
 * 1. 加@Data注解<br>
 * 2. 这种对象只允许用作mysql数据库访问，严禁在其他地方使用<br>
 * </p>
 */
public interface PersistentObject {
}
