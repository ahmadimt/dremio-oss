/*
 * Copyright (C) 2017-2018 Dremio Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dremio.exec.store.dfs;

import java.util.List;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.dremio.exec.catalog.conf.Property;
import com.dremio.exec.catalog.conf.ConnectionConf;
import com.dremio.exec.store.StoragePlugin;
import com.google.common.collect.ImmutableList;

public abstract class FileSystemConf<C extends FileSystemConf<C, P>, P extends StoragePlugin> extends ConnectionConf<C, P>{

  public abstract Path getPath();

  public abstract boolean isImpersonationEnabled();

  public abstract List<Property> getProperties();

  public abstract String getConnection();

  public abstract SchemaMutability getSchemaMutability();

  /**
   * Whether the plugin should automatically create the requested path if it doesn't already exist.
   * @return {@code true} if a missing path should be created.
   */
  public boolean createIfMissing() {
    return false;
  }

  /**
   * List of properties that are unique to the {@link FileSystem} objects. This are in addition to the URI and user.
   * Examples include ADLS password, S3 access key and secret etc.
   * @return
   */
  public List<String> getConnectionUniqueProperties() {
    return ImmutableList.of();
  }
}
