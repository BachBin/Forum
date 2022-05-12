/*CKEDITOR.editorConfig = function (config) {
    // Define changes to default configuration here. For example:
    config.language = 'en';
    // config.uiColor = '#AADC6E';
    config.htmlEncodeOutput = false;
    config.entities = false;
    config.entities_latin = false;
    config.ForceSimpleAmpersand = true;
    config.allowedContent = true;
};*/


CKEDITOR.editorConfig = function( config )
{
    config.toolbar_TRiGCustom =
    [
        ['Bold','Italic','Underline','-','JustifyLeft','JustifyCenter','-','Blockquote'],
        ['FontSize'],
        ['Undo','Redo'],
        ['Link','Unlink','Image','Table'],
        ['NumberedList', 'BulletedList'],
        ['Source'],
        ['Maximize']
    ];   
    config.forcePasteAsPlainText = true;
    config.forceSimpleAmpersand = true;
    config.resize_enabled = false;
    config.toolbarCanCollapse = false;
    config.scayt_autoStartup = true;
    config.language = 'en';
    config.uiColor = '#76BC49';
    config.width = '100%';
    config.extraPlugins = 'maximize';
    config.allowedContent = true;
};