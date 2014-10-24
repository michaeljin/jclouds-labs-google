/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.oauth.v2.filters;

import com.google.common.base.Supplier;
import org.jclouds.http.HttpException;
import org.jclouds.http.HttpRequest;
import org.jclouds.oauth.v2.domain.OAuthCredentials;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BearerTokenAuthenticator implements OAuthAuthenticationFilter {
   private final Supplier<OAuthCredentials> creds;

   @Inject
   BearerTokenAuthenticator(final Supplier<OAuthCredentials> creds) {
      this.creds = creds;
   }

   @Override
   public HttpRequest filter(HttpRequest request) throws HttpException {
      return request.toBuilder().addHeader("Authorization", String.format("%s %s",
            "Bearer", creds.get().credential)).build();
   }
}
