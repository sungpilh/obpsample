package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class View {
    private String name;  //Name of view to create  , not editable 
    private String description; //Description of view (this example is public, uses the public alias, and has limited access to account data)
    private boolean is_public;  //
    private String which_alias_to_use;  //_public_
    private boolean hide_metadata_if_alias_used;
    private String[] allowed_actions;

    public View(String name, String description, boolean is_public, String which_alias_to_use, boolean hide_metadata_if_alias_used, String[] allowed_actions) {

        this.name = name;
        this.description = description;
        this.is_public = is_public;
        this.which_alias_to_use = which_alias_to_use;
        this.hide_metadata_if_alias_used = hide_metadata_if_alias_used;
        this.allowed_actions = allowed_actions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_public() {
        return is_public;
    }

    public void setIs_public(boolean is_public) {
        this.is_public = is_public;
    }

    public String getWhich_alias_to_use() {
        return which_alias_to_use;
    }

    public void setWhich_alias_to_use(String which_alias_to_use) {
        this.which_alias_to_use = which_alias_to_use;
    }

    public boolean isHide_metadata_if_alias_used() {
        return hide_metadata_if_alias_used;
    }

    public void setHide_metadata_if_alias_used(boolean hide_metadata_if_alias_used) {
        this.hide_metadata_if_alias_used = hide_metadata_if_alias_used;
    }

    public String[] getAllowed_actions() {
        return allowed_actions;
    }

    public void setAllowed_actions(String[] allowed_actions) {
        this.allowed_actions = allowed_actions;
    }
}
