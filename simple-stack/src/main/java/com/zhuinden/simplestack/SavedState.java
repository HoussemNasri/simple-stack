/*
 * Copyright 2017 Gabor Varadi
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
package com.zhuinden.simplestack;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.zhuinden.statebundle.StateBundle;

/**
 * A container for the view hierarchy state and an optional Bundle.
 * Made to be used with {@link BackstackDelegate}'s view state persistence.
 *
 * A {@link SavedState} represents the state of the view that is bound to a given key.
 */
public class SavedState {
    private Object key;
    private SparseArray<Parcelable> viewHierarchyState;
    private StateBundle bundle;

    private SavedState() {
    }

    public Object getKey() {
        return key;
    }

    public SparseArray<Parcelable> getViewHierarchyState() {
        return viewHierarchyState;
    }

    public void setViewHierarchyState(SparseArray<Parcelable> viewHierarchyState) {
        this.viewHierarchyState = viewHierarchyState;
    }

    public StateBundle getBundle() {
        return bundle;
    }

    public void setBundle(StateBundle bundle) {
        this.bundle = bundle;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * A builder class that allows creating SavedState instances.
     *
     * Keys are not optional.
     */
    public static class Builder {
        private Object key;
        private SparseArray<Parcelable> viewHierarchyState = new SparseArray<>();
        private StateBundle bundle;

        Builder() {
        }

        public Builder setKey(@NonNull Object key) {
            if(key == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }
            this.key = key;
            return this;
        }

        public Builder setViewHierarchyState(@NonNull SparseArray<Parcelable> viewHierarchyState) {
            if(viewHierarchyState == null) {
                throw new IllegalArgumentException("Provided sparse array for view hierarchy state cannot be null");
            }
            this.viewHierarchyState = viewHierarchyState;
            return this;
        }

        public Builder setBundle(@Nullable StateBundle bundle) {
            this.bundle = bundle;
            return this;
        }

        public SavedState build() {
            if(key == null) {
                throw new IllegalStateException("You cannot create a SavedState without associating a Key with it.");
            }
            SavedState savedState = new SavedState();
            savedState.key = key;
            savedState.viewHierarchyState = viewHierarchyState;
            savedState.bundle = bundle;
            return savedState;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof SavedState)) {
            return false;
        }
        return ((SavedState)obj).getKey().equals(this.key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }
}
