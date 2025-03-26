package com.nullptrexc.model.domain.mcmeta;

import com.nullptrexc.model.domain.PackFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pack {
    @NotNull
    private PackFormat pack_format;
    @Nullable
    private ArrayList<PackFormat> supported_formats;
    @NotNull
    private String description;

    public Pack(@NotNull PackFormat pack_format, @Nullable ArrayList<PackFormat> supported_formats, @NotNull String description) {
        this.pack_format = pack_format;
        this.supported_formats = supported_formats;
        this.description = description;
    }

    public Pack(@NotNull PackFormat pack_format, @NotNull String description) {
        this.pack_format = pack_format;
        this.description = description;
    }

    /**
     * Creates a Pack object from a JSON object.
     *
     * @param json the JSON object containing the pack data
     * @return a Pack object created from the JSON data
     */
    public static Pack fromJson(JSONObject json) {
        if(json.optJSONArray("supported_formats") == null) {
            return new Pack(
                    PackFormat.getFormatByPackFormat(json.getInt("pack_format")),
                    json.getString("description")
            );
        }
        return new Pack(
                PackFormat.getFormatByPackFormat(json.getInt("pack_format")),
                PackFormat.convertJsonArrayToList(json.optJSONArray("supported_formats")),
                json.getString("description")
        );
    }


    @Override
    public String toString() {
        return "Pack{" +
                "pack_format=" + pack_format +
                ", supported_formats=" + supported_formats +
                ", description='" + description + '\'' +
                '}';
    }

    public PackFormat getPackFormat() {
        return pack_format;
    }

    public Pack setPackFormat(PackFormat pack_format) {
        this.pack_format = pack_format;
        return this;
    }

    public ArrayList<PackFormat> getSupportedFormats() {
        return supported_formats;
    }

    public Pack setSupportedFormats(ArrayList<PackFormat> supported_formats) {
        this.supported_formats = supported_formats;
        return this;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public Pack setDescription(String description) {
        this.description = description;
        return this;
    }
}
